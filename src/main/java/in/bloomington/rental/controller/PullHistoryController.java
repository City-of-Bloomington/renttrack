package in.bloomington.rental.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import in.bloomington.rental.model.PullHistory;
import in.bloomington.rental.model.PullReason;
import in.bloomington.rental.model.RentUser;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.service.PullHistoryService;
import in.bloomington.rental.service.PullReasonService;
import in.bloomington.rental.service.RentalService;

@Controller
public class PullHistoryController {

		String message = null;
		static List<PullReason> reasons = null;
		
		@Autowired
		private PullHistoryService pullHistoryService;
		@Autowired
		private RentalService rentalService;
		@Autowired
		private PullReasonService pullReasonService;

		@Autowired
		private HttpSession session;
		
		@GetMapping("/pulls/{rental_id}")
		public String pullsView(@PathVariable("rental_id") int rental_id,
														Model model) {
				
				model.addAttribute("pulls", pullHistoryService.getPullHistoryForRental(rental_id));
				if(message != null)
						model.addAttribute("message", message);
				return "pulls";
		}
		// edit by id
		@GetMapping("/pullEdit/{id}")
		public String editPull(@PathVariable("id") int id,
											Model model) {
				PullHistory pull = pullHistoryService.get(id);
				model.addAttribute("pull", pull);
				if(reasons == null){
						reasons = pullReasonService.list();
				}
				model.addAttribute("reasons",reasons);
			 return "pullEdit";
		}
		
		// update
		@PostMapping("/pullUpdate")
		public String updatePull(@ModelAttribute("pull") @Valid PullHistory pull,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/pullEdit/"+pull.getId();
				}
				if(pull.isDone() && !pull.hasCompletedDate()){
						pull.setCompletedDate(new Date()); // today
				}
				pullHistoryService.update(pull.getId(), pull);
				message = "Pull History updated successfully";
				return "redirect:/view/"+pull.getRental().getId();
		}

		@GetMapping("/pullNew/{rental_id}")
		public String pullForm(@PathVariable("rental_id") int rental_id,
													 Model model
													 ) {
				Rental rental = rentalService.get(rental_id);
				PullHistory pull = new PullHistory();
				pull.setRental(rental);
				model.addAttribute("pull", pull);
				if(reasons == null){
						reasons = pullReasonService.list();
				}
				model.addAttribute("reasons",reasons);				
				return "pullNew";
   }

		// save
		@PostMapping("/pullSave")
   public String savePull(@ModelAttribute("pull") @Valid PullHistory pull,
													BindingResult result, Model model
													) {
      if (result.hasErrors()) {
         return "redirect:/pullNew";
      }
			pull.setDateNow();
			if(session != null){
					RentUser user = (RentUser)session.getAttribute("user");
					if(user != null){
							pull.setUser(user);
					}
			}			
			pullHistoryService.save(pull);
			Rental rental = pull.getRental();
			message = "Pull history saved successfully";
			return "redirect:/view/"+rental.getId(); 					
   }

}
