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
import org.springframework.http.ResponseEntity;
import org.springframework.context.annotation.Scope;

import in.bloomington.rental.model.Variance;
import in.bloomington.rental.service.VarianceService;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.RentUser;
import in.bloomington.rental.service.RentalService;

@Controller
@Scope("session")
public class VarianceController {

		String message = null;
		
		@Autowired
		private VarianceService varianceService;
		@Autowired
		private RentalService rentalService;		

		@Autowired
		HttpSession session;
		@GetMapping("/variances")
		public String variancesView(Locale locale, Model model) {
				
				model.addAttribute("variances", varianceService.getAll());
				if(message != null)
						model.addAttribute("message", message);
				return "variances";
		}
		// get by id
		@GetMapping("/variance/{id}")
		public String getVariance(@PathVariable("id") int id,
											Model model) {
				Variance variance = varianceService.get(id);
				model.addAttribute("variance", variance);
			 return "varianceView";
		}
		// edit by id
		@GetMapping("/varianceEdit/{id}")
		public String editVariance(@PathVariable("id") int id,
											Model model) {
				Variance variance = varianceService.get(id);
				model.addAttribute("variance", variance);
			 return "varianceEdit";
		}
		// get by id
		@GetMapping("/varianceDelete/{id}")
		public String deleteVariance(@PathVariable("id") int id) {
				System.err.println(" id "+id);
				Variance variance = varianceService.get(id);
				int rentalId = variance.getRental().getId();
				varianceService.delete(id);
				message = "Variance deleted successfully";
			 return "redirect:/view/"+rentalId;
		}		
		// save
		@PostMapping("/varianceUpdate")
		public String updateVariance(@ModelAttribute("variance") @Valid Variance variance,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/varianceEdit/"+variance.getId();
				}
				varianceService.update(variance.getId(), variance);
				message = "Variance updated successfully";
				return "redirect:/view/"+variance.getRental().getId();
		}		
		@GetMapping("/varianceNew/{rental_id}")
		public String varianceForm(@PathVariable("rental_id") int rental_id,Model model) {
				Rental rental = rentalService.get(rental_id);
				Variance variance = new Variance();
				variance.setRental(rental);
				model.addAttribute("variance", variance);
				return "varianceNew";
   }

		// save
		@PostMapping("/varianceSave")
   public String saveVariance(@ModelAttribute("variance") @Valid Variance variance,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/varianceNew";
      }
			RentUser user = null;
			if(session != null){
					user = (RentUser)session.getAttribute("user");
			}					
			variance.setUser(user);
      varianceService.save(variance);
			Rental rental = variance.getRental();
			message = "Variance saved successfully";
			return "redirect:/view/"+rental.getId(); 
   }

}
