package in.bloomington.rental.controller;

import java.util.Locale;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.json.JSONObject;
import org.json.JSONArray;
import in.bloomington.rental.model.Item;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.model.OwnerPhone;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.RentalOwner;
import in.bloomington.rental.service.OwnerService;
import in.bloomington.rental.service.RentalService;


@Controller
public class OwnerController {

		String message = null;
		
		@Autowired
		private OwnerService ownerService;
		@Autowired
		private RentalService rentalService;
		
		@GetMapping("/settings")
		public String getSettings(Locale locale, Model model) {
				return "settings";
		}
		
		@GetMapping("/owners")
		public String ownersView(Locale locale, Model model) {
				
				model.addAttribute("owners", ownerService.getAll());
				if(message != null)
						model.addAttribute("message", message);
				return "owners";
		}
		// get by id
		@GetMapping("/owner/{id}")
		public String getOwner(@PathVariable("id") int id,
											Model model) {
				Owner owner = ownerService.get(id);
				model.addAttribute("owner", owner);
				if(owner.hasPhones()){
						model.addAttribute("phones", owner.getOwnerPhones());
				}
			 return "ownerView";
		}
		// edit by id
		@GetMapping("/ownerEdit/{id}")
		public String editOwner(@PathVariable("id") int id,
											Model model) {
				Owner owner = ownerService.get(id);
				model.addAttribute("owner", owner);
				if(owner.hasPhones()){
						model.addAttribute("phones", owner.getOwnerPhones());
				}				
			 return "ownerEdit";
		}
		//
		// get by id
		@GetMapping("/ownerDelete/{id}")
		public String deleteOwner(@PathVariable("id") int id) {
				ownerService.delete(id);
				message = "Owner deleted successfully";
			 return "redirect:/owners";
		}		
		// save
		@PostMapping("/ownerUpdate")
		public String updateOwner(@ModelAttribute("owner") @Valid Owner owner,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/ownerEdit/"+owner.getId();
				}
				ownerService.update(owner.getId(), owner);
				message = "Owner updated successfully";
				return "redirect:/owner/"+owner.getId();
		}		
		@GetMapping("/owner")
		public String ownerNew(Locale locale, Model model) {
				model.addAttribute("owner", new Owner());
				return "ownerNew";
   }
		// rental id
		@GetMapping("/ownerWithLink/{id}")
		public String ownerNewWithLink(@PathVariable("id") int id, Model model) {
				
				model.addAttribute("owner", new Owner());
				model.addAttribute("rentalId",id);
				return "ownerNewWithLink";
   }		
		@GetMapping("/addOwners/{rental_id}")
		public String addOwner(@PathVariable("rental_id") int rental_id,
													 Model model){
				Rental rental = rentalService.get(rental_id);
				RentalOwner rentalOwner = new RentalOwner();
				Owner owner = new Owner();
				rentalOwner.setRental(rental);
				rentalOwner.setOwner(owner);
				model.addAttribute("rentalOwner", rentalOwner);
				model.addAttribute("rental", rental);				
				return "ownersAdd";
   }
		// save rentalOwner
		@PostMapping("/rentalOwnerSave")
		public String saveOwners(@ModelAttribute("rentalOwner")
														 @Valid RentalOwner rentalOwner,
														 BindingResult result,
														 Model model) {
      if (result.hasErrors()) {
         return "redirect:/owner";
      }
      ownerService.save(rentalOwner);
		  int rental_id = rentalOwner.getRental().getId();
			message = "Owner saved successfully";
      return "redirect:/addOwners/"+rental_id;
   }		
		// save
		@PostMapping("/ownerSave")
   public String saveOwner(@ModelAttribute("owner") @Valid Owner owner,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/owner";
      }
			message = "Owner saved successfully";
      ownerService.save(owner);
      return "redirect:/owner/"+owner.getId();
   }
		// save
		@PostMapping("/ownerSaveWithLink")
   public String saveOwnerWithLink(@ModelAttribute("owner") @Valid Owner owner,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/owner";
      }
			message = "Owner saved successfully";
			int rentalId = owner.getRentalId();			
      ownerService.save(owner);
			Rental rental = rentalService.get(rentalId);
			RentalOwner rentalOwner = new RentalOwner();
			rentalOwner.setOwner(owner);
			rentalOwner.setRental(rental);
			ownerService.save(rentalOwner);
      return "redirect:/view/"+rentalId;
   }		
		// remove rentalOwner id
		@GetMapping("/ownerRemove/{id}")
		public String ownerRemove(@PathVariable("id") int id) {
				RentalOwner rentalOwner = ownerService.getRentalOwner(id);
				int rental_id = rentalOwner.getRental().getId();
				ownerService.removeRentalOwner(id); 
				message = "Rental removed from owner successfully";
			 return "redirect:/view/"+rental_id;
		}
		@GetMapping("/searchOwner")
		public String ownerSearch(Locale locale, Model model) {
				model.addAttribute("owner", new Owner());
				if(message != null)
						model.addAttribute("message", message);
				return "searchOwner";
   }
		@PostMapping("/ownersFind")
		public String ownersFind(@ModelAttribute("owner") @Valid Owner owner,
														 BindingResult result, Model model) {
				List<Owner> owners = ownerService.search(owner);
				message = null;
				if(owners == null || owners.size() == 0){
						message = "No match found ";
						return "redirect:/searchOwner";
				}
				else if(owners.size() == 1){
						return "redirect:/owner/"+owners.get(0).getId();
				}
				message = "Found "+owners.size()+" records";
				model.addAttribute("message",message);
				model.addAttribute("owners", owners);
				return "owners";
		}		
}

@RestController		
class OwnerServiceController {
		
		@Autowired
		private OwnerService ownerService;
		@GetMapping(value="/ownerService", produces = "application/json")
		public String ownerService(@RequestParam("term") String term,
															 Locale locale, Model model) {
				String json = "";
				// System.err.println(" term "+term);
				if(term != null && term.length() > 2){
						List<Item> owners = ownerService.getList(term);
						if(owners != null && owners.size() > 0){
								json = buildJson(owners);
								System.err.println(json);
						}
				}
				return json;
		}
		@GetMapping(value="/agentService", produces = "application/json")
		public String agentService(@RequestParam("term") String term,
															 Locale locale, Model model) {
				String json = "";
				if(term != null && term.length() > 2){
						List<Item> agents = ownerService.getAgentList(term);
						if(agents != null && agents.size() > 0){
								json = buildJson(agents);
								System.err.println(json);
						}
				}
				return json;
		}

		private String buildJson(List<Item> owners){
				String json = "";
				JSONArray jArr = new JSONArray();
				for(Item one:owners){
						JSONObject jObj = new JSONObject();
						jObj.put("id",one.getId());
						jObj.put("value",one.getName());
						
						jArr.put(jObj);
				}
				json = jArr.toString();
				return json;
		}		
}
