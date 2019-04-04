package in.bloomington.rental.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import in.bloomington.rental.model.Address;
import in.bloomington.rental.model.Legal;
import in.bloomington.rental.model.LegalType;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.model.OwnerPhone;
import in.bloomington.rental.model.PullHistory;
import in.bloomington.rental.model.RentUser;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.RentalLegal;
import in.bloomington.rental.model.RentalOwner;
import in.bloomington.rental.service.AddressService;
import in.bloomington.rental.service.OwnerService;
import in.bloomington.rental.service.RentalLegalService;
import in.bloomington.rental.service.RentalService;
import in.bloomington.rental.util.LegalHandle;

@Controller
@Scope("session")
public class LegalController
{

    String                     message    = null;
    List<LegalType>            legalTypes = null;
    @Autowired
    private RentalService      rentalService;

    @Autowired
    private OwnerService       ownerService;

    @Autowired
    private RentalLegalService legalService;

    @Autowired
    private AddressService     addressService;

    @Autowired
    private LegalHandle        legalHandle;

    @Autowired
    private HttpSession        session;

    @GetMapping("/startLegal/{id}")
    public String startLegal(@PathVariable("id") int id, Model model)
    {
        Legal       legal  = new Legal();
        Rental      rental = rentalService.get(id);
        if (rental.hasOwners()) {
            List<RentalOwner> rones = rental.getRentalOwners();
            for (RentalOwner rone : rones) {
                legal.addOwner(rone.getOwner());
            }
        }
        List<PullHistory> pulls      = rental.getPullHistories();
        String            pullDate   = "";
        String            pullReason = "";
        if (pulls != null && pulls.size() > 0) {
            PullHistory lastPull = pulls.get(0); // last in desc order
            pullDate   = lastPull.getDateFr();
            pullReason = lastPull.getPullReason().getReason();
            legal.setPullDate(pullDate);
            legal.setPullReason(pullReason);
        }
        List<Address> addresses = addressService.findByRentalId(id);
        if (addresses != null && addresses.size() > 0) {
            legal.setAddresses(addresses);
        }

        if (legalTypes == null) {
            legalHandle.findLegalTypes();
            if (legalHandle.foundTypes()) {
                legalTypes = legalHandle.getLegalTypes();
            }
        }
        RentUser user = null;
        if (session != null) {
            user = (RentUser) session.getAttribute("user");
        }
        legal.setRental(rental);
        legal.setUser(user);
        model.addAttribute("legal", legal);
        System.err.println("in legal: user " + user);
        if (legalTypes != null) {
            model.addAttribute("legalTypes", legalTypes);
        }
        return "startLegal";
    }

    @PostMapping("/legalSave")
    public String legalSave(@ModelAttribute("legal") Legal legal,
                            BindingResult result,
                            Model         model)
    {
        if (result.hasErrors()) {
            System.err.println(" errors " + result.toString());
            return "redirect:/startLegal/" + legal.getRental_id();
        }
        if (legal.isValid()) {
            int        legal_id  = 0;
            int        rental_id = legal.getRental_id();
            JSONObject json      = composeJson(legal);
            String     back      = legalHandle.doLegalPost(json);
            System.err.println(back);
            if (back.indexOf("'") > -1) {
                String str = back.substring(back.indexOf("'") + 1, back.lastIndexOf("'"));
                try {
                    legal_id = Integer.valueOf(str);
                    System.err.println("legal_id " + legal_id);
                }
                catch (Exception ex) {
                    System.err.println(ex);
                }
            }
            if (legal_id > 0) {
                RentUser user = null;
                if (session != null) {
                    user = (RentUser) session.getAttribute("user");
                }
                RentalLegal rlegal = new RentalLegal();
                rlegal.setId(legal_id);
                Rental rental = rentalService.get(rental_id);
                rlegal.setRental(rental);
                rlegal.setStartByUser(user);
                rlegal.setDate(new Date());
                legalService.save(rlegal);
                //
                // email will be sent from legaltrack
                //
            }
        }
        message = "Saved successfully";
        model.addAttribute("message", message);
        return "messageOnly";
    }

    /**
     * create a json object from the data
     */
    JSONObject composeJson(Legal legal)
    {
        Integer[]     ownerIds   = legal.getOwner_ids();
        Integer[]     addressIds = legal.getAddress_ids();
        List<Owner>   owners     = new ArrayList<>();
        List<Address> addresses  = new ArrayList<>();
        for (Integer oid : ownerIds) {
            Owner owner = ownerService.get(oid);
            if (owner != null) {
                owners.add(owner);
            }
        }
        for (Integer aid : addressIds) {
            Address addr = addressService.get(aid);
            if (addr != null) {
                addresses.add(addr);
            }
        }
        JSONObject jObj = new JSONObject();
        jObj.put("rental_id", "" + legal.getRental_id());
        jObj.put("startBy",   legal.getStartBy());
        jObj.put("startDate", legal.getStartDate());
        jObj.put("attention", legal.getAttention());
        jObj.put("reason",    legal.getReason());
        jObj.put("pull_reason", legal.getPullReason());
        jObj.put("pull_date", legal.getPullDate());
        jObj.put("case_type", legal.getCase_type());
        jObj.put("status", legal.getStatus());
        jObj.put("case_status", legal.getCase_status());
        jObj.put("action", "Save");
        if (owners != null) {
            JSONArray jArr = new JSONArray();
            for (Owner one : owners) {
                JSONObject jObjOnr = new JSONObject();
                jObjOnr.put("name", one.getName());
                jObjOnr.put("address", one.getAddress());
                jObjOnr.put("city", one.getCity());
                jObjOnr.put("state", one.getState());
                jObjOnr.put("email", one.getOneEmail()); // if there are more than one
                if (one.hasPhones()) {
                    Set<OwnerPhone>  phoneSet = one.getOwnerPhones();
                    List<OwnerPhone> phones   = new ArrayList<OwnerPhone>(phoneSet);
                    if (phones != null) { // we need only 1 or 2
                        if (phones.size() > 0) {
                            OwnerPhone pp = phones.get(0);
                            jObjOnr.put("phone", pp.getPhoneNum());
                        }
                        if (phones.size() > 1) {
                            OwnerPhone pp = phones.get(1);
                            jObjOnr.put("phone_2", pp.getPhoneNum());
                        }
                    }
                }
                jArr.put(jObjOnr);
            }
            jObj.put("owners", jArr);
        }
        if (addresses != null) {
            JSONArray jArr = new JSONArray();
            for (Address one : addresses) {
                JSONObject jaObj = new JSONObject();
                jaObj.put("street_address", one.getStreetAddress());
                jaObj.put("invalid", one.isInvalidAddr());
                jArr.put(jaObj);
            }
            jObj.put("addresses", jArr);
        }
        return jObj;
    }
}
