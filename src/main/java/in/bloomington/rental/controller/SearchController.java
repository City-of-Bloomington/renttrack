package in.bloomington.rental.controller;

import java.util.Locale;
import java.util.List;
import java.util.Date;
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

import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.RentalStatus;
import in.bloomington.rental.model.Zoning;
import in.bloomington.rental.model.RentUser;
import in.bloomington.rental.util.Search;
import in.bloomington.rental.service.RentalService;
import in.bloomington.rental.service.RentalStatusService;
import in.bloomington.rental.service.ZoningService;
import org.springframework.context.annotation.Scope;


@Controller
@Scope("session")
public class SearchController {

		String message = null;
		final static int[] nhoods = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		static List<Zoning> zonings = null;
		static List<RentalStatus> statuses = null;

		@Autowired
		private RentalService rentalService;
		
		@Autowired
		private RentalStatusService statusService;
		
		@Autowired
		private ZoningService zoningService;
		
		@Autowired
		private RentUser user;
		private Search search = null;
		@GetMapping("/search")
		public String searchRental(Model model) {
				if(search == null)
						search = new Search();
				model.addAttribute("search", search);
				model.addAttribute("nhoods", nhoods);
				if(zonings == null){
						zonings = zoningService.list();
				}
				if(zonings != null){
						model.addAttribute("zonings", zonings);
				}
				if(statuses == null){
						statuses = statusService.list();
				}
				if(statuses != null){
						model.addAttribute("statuses", statuses);
				}
				if(message != null)
						model.addAttribute("message", message);				
			 return "search";
		}

		@PostMapping("/rentalSearch")
		public String updateRental(@ModelAttribute("search") @Valid Search search,
															 Model model) {

				List<Rental> rentals = rentalService.search(search);
				if(rentals == null){
						message = "No match found";
						return "redirect:/search";
				}
				else if(rentals.size() == 1){
						int rentalId = rentals.get(0).getId();
						return "redirect:/view/"+rentalId;
				}
				else{
						message = "Found "+rentals.size()+" records";
						model.addAttribute("rentals", rentals);
						model.addAttribute("message", message);
						return "rentals"; 						
				}
		}		

}
