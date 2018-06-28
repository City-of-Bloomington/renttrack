package in.bloomington.rental.controller;

import java.util.Locale;
import java.util.List;
import javax.validation.Valid;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import in.bloomington.rental.model.RentUser;
import in.bloomington.rental.model.StandardFees;
import in.bloomington.rental.service.StandardFeesService;

@Controller
public class StandardFeesController {

		String message = null;
		
		@Autowired
		private StandardFeesService standardFeesService;

		@Autowired
		private HttpSession session;
		
		@GetMapping("/settings/standardFeeses")
		public String standardFeesView(Locale locale, Model model) {
				
				model.addAttribute("standardFeeses", standardFeesService.getAll());
				if(message != null)
						model.addAttribute("message", message);
				return "/settings/standardFeeses";
		}
		// edit by id
		@GetMapping("/settings/standardFees/{id}")
		public String editStandardFees(@PathVariable("id") int id,
											Model model) {
				StandardFees standardFees = standardFeesService.get(id);
				model.addAttribute("standardFees", standardFees);
			 return "/settings/standardFees";
		}
		// save
		@PostMapping("/settings/standardFeesUpdate")
		public String updateStandardFees(@ModelAttribute("standardFees") @Valid StandardFees standardFees,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/settings/standardFees/"+standardFees.getId();
				}
				RentUser user = null;
				if(session != null){
						user = (RentUser)session.getAttribute("user");
				}
				standardFees.setUser(user);
				standardFeesService.update(standardFees.getId(), standardFees);
				int id = standardFees.getId();
				message = "standardFees updated successfully";
				return "redirect:/settings/standardFees/"+id;
		}		
		@GetMapping("/settings/standardFeesNew")
		public String standardFeesForm(Locale locale, Model model) {

				model.addAttribute("standardFees", new StandardFees());
				return "/settings/standardFeesNew";
   }

		// save
		@PostMapping("/settings/standardFeesSave")
   public String saveStandardFees(@ModelAttribute("standardFees") @Valid StandardFees standardFees,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/settings/standardFeesNew";
      }
			RentUser user = null;
			if(session != null){
					user = (RentUser)session.getAttribute("user");
			}
			standardFees.setUser(user);
      standardFeesService.save(standardFees);
			int id = standardFees.getId();
			message = "StandardFees saved successfully";
      return "redirect:/settings/standardFees/"+id;
   }
}

