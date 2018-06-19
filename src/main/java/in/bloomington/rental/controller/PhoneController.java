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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import in.bloomington.rental.model.OwnerPhone;
import in.bloomington.rental.service.OwnerPhoneService;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.service.OwnerService;

@Controller
public class PhoneController {

		String message = null;
		final static String[] phoneTypes = {"Home","Work","Cell"};
		
		@Autowired
		private OwnerPhoneService phoneService;
		@Autowired
		private OwnerService ownerService;
		
		@GetMapping("/phones")
		public String phonesView(Locale locale, Model model) {
				
				model.addAttribute("phones", phoneService.list());
				if(message != null)
						model.addAttribute("message", message);
				return "phones";
		}
		// get by id
		@GetMapping("/phone/{id}")
		public String getPhone(@PathVariable("id") int id,
											Model model) {
				System.err.println(" id "+id);
				OwnerPhone phone = phoneService.get(id);
				model.addAttribute("phone", phone);
			 return "phoneView";
		}
		// edit by id
		@GetMapping("/phoneEdit/{id}")
		public String editPhone(@PathVariable("id") int id,
											Model model) {
				System.err.println(" id "+id);
				OwnerPhone phone = phoneService.get(id);
				model.addAttribute("phone", phone);
			 return "phoneEdit";
		}
		// get by id
		@GetMapping("/phoneDelete/{id}")
		public String deletePhone(@PathVariable("id") int id) {
				System.err.println(" id "+id);
				OwnerPhone phone = phoneService.get(id);
				int owner_id = phone.getOwner().getId();
				phoneService.delete(id);
				message = "Phone deleted successfully";
			 return "redirect:/owner/"+owner_id;
		}		
		// update
		@PostMapping("/phoneUpdate")
		public String updatePhone(@ModelAttribute("phone") @Valid OwnerPhone phone,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/phoneEdit/"+phone.getId();
				}
				phoneService.update(phone.getId(), phone);
				message = "Phone updated successfully";
				return "redirect:/owner/"+phone.getOwner().getId();
		}		
		@GetMapping("/phoneNew/{owner_id}")
		public String phoneForm(@PathVariable("owner_id") int owner_id,
														Locale locale, Model model) {
				Owner owner = ownerService.get(owner_id);				
				OwnerPhone phone = new OwnerPhone();
				phone.setOwner(owner);
				model.addAttribute("phone", phone);
				model.addAttribute("phoneTypes",phoneTypes);
				return "phoneNew";
   }

		// save
		@PostMapping("/phoneSave")
   public String savePhone(@ModelAttribute("phone") @Valid OwnerPhone phone,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/phoneNew";
      }
			message = "Phone saved successfully";
			Owner owner = phone.getOwner();
      phoneService.save(phone);
      return "redirect:/owner/"+owner.getId();
   }

}
