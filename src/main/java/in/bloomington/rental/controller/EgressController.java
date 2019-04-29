package in.bloomington.rental.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.bloomington.rental.model.Egress;
import in.bloomington.rental.service.EgressService;

@Controller
public class EgressController
{

    String                message = null;

    @Autowired
    private EgressService egressService;

    @GetMapping("/settings/egresses")
    public String egressView(Locale locale, Model model)
    {

        model.addAttribute("egresses", egressService.getAll());
        if (message != null) model.addAttribute("message", message);
        return "/settings/egresses";
    }

    // edit by id
    @GetMapping("/settings/egress/{id}")
    public String editEgress(@PathVariable("id") int id, Model model)
    {
        Egress egress = egressService.get(id);
        model.addAttribute("egress", egress);
        return "/settings/egress";
    }

    // save
    @PostMapping("/settings/egressUpdate")
    public String updateEgress(@ModelAttribute("egress") @Valid Egress egress,
                               BindingResult result,
                               Model         model)
    {
        if (result.hasErrors()) {
            System.err.println(" errors " + result.toString());
            return "redirect:/settings/egress/" + egress.getId();
        }
        egressService.update(egress.getId(), egress);
        message = "egress updated successfully";
        return "redirect:/settings/egresses";
    }

    @GetMapping("/settings/egressNew")
    public String egressForm(Locale locale, Model model)
    {

        model.addAttribute("egress", new Egress());
        return "/settings/egressNew";
    }

    // save
    @PostMapping("/settings/egressSave")
    public String saveEgress(@ModelAttribute("egress") @Valid Egress egress,
                             BindingResult result,
                             Model         model)
    {
        if (result.hasErrors()) {
            return "redirect:/settings/egressNew";
        }
        message = "Rental Status saved successfully";
        egressService.save(egress);
        return "redirect:/settings/egresses";
    }
}

@RestController
class EgressServiceController
{
    @Autowired
    private EgressService egressService;

    @GetMapping(value = "/egressService", produces = "application/json")
    public String egressService(@RequestParam("term") String term,
                                                      Locale locale,
                                                      Model  model)
    {
        String json = "";
        if (term != null && term.length() > 3) {
            int year = 0;
            try {
                year = Integer.parseInt(term);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
            List<Egress> egresses = egressService.findByYear(year);
            if (egresses != null && egresses.size() > 0) {
                json = buildJson(egresses, term);
                // System.err.println(json);
            }
        }
        return json;
    }

    private String buildJson(List<Egress> egresses, String year)
    {
        String    json = "";
        JSONArray jArr = new JSONArray();
        for (Egress one : egresses) {
            JSONObject jObj = new JSONObject();
            // jObj.put("id", one.getId());
            jObj.put("value",        year + " " + one.getType());
            jObj.put("year",         year);
            jObj.put("type",         one.getType());
            jObj.put("startEndYear", one.getStartEndYear());
            jObj.put("height",       one.getHeight());
            jObj.put("width",        one.getWidth());
            jObj.put("sillHeight",   one.getSillHeight());
            jObj.put("area",         one.getArea());
            if (one.getArea2() != null) {
                jObj.put("area2", one.getArea2());
            }
            jArr.put(jObj);
        }
        json = jArr.toString();
        return json;
    }
}
