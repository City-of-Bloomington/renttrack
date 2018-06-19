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

import in.bloomington.rental.model.Zoning;
import in.bloomington.rental.service.ZoningService;


@Controller
public class ZoningController {

		String message = null;
		
		@Autowired
		private ZoningService zoningService;
		
		@GetMapping("/settings/zonings")
		public String zoningView(Locale locale, Model model) {
				
				model.addAttribute("zonings", zoningService.list());
				if(message != null)
						model.addAttribute("message", message);
				return "/settings/zonings";
		}
		// edit by id
		@GetMapping("/settings/zoning/{id}")
		public String editZoning(@PathVariable("id") int id,
											Model model) {
				Zoning zoning = zoningService.get(id);
				model.addAttribute("zoning", zoning);
			 return "/settings/zoningEdit";
		}
		// save
		@PostMapping("/settings/zoningUpdate")
		public String updateZoning(@ModelAttribute("zoning") @Valid Zoning zoning,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/settings/zoningEdit/"+zoning.getId();
				}
				zoningService.update(zoning.getId(), zoning);
				message = "Zoning updated successfully";
				return "redirect:/settings/zonings";
		}		
		@GetMapping("/settings/zoningNew")
		public String zoningForm(Locale locale, Model model) {

				model.addAttribute("zoning", new Zoning());
				return "/settings/zoningNew";
   }

		// save
		@PostMapping("/settings/zoningSave")
   public String saveZoning(@ModelAttribute("zoning") @Valid Zoning zoning,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/settings/zoningNew";
      }
			message = "Rental Status saved successfully";
      zoningService.save(zoning);
      return "redirect:/settings/zonings";
   }

}
