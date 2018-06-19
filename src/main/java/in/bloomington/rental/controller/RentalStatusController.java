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

import in.bloomington.rental.model.RentalStatus;
import in.bloomington.rental.service.RentalStatusService;


@Controller
public class RentalStatusController {

		String message = null;

		@Autowired
		private RentalStatusService rentalStatusService;
		
		@GetMapping("/settings/rentalStatuses")
		public String rentalStatusView(Locale locale, Model model) {
				
				model.addAttribute("rentalStatuses", rentalStatusService.list());
				if(message != null)
						model.addAttribute("message", message);
				return "/settings/rentalStatuses";
		}
		// edit by id
		@GetMapping("/settings/rentalStatus/{id}")
		public String editRentalStatus(@PathVariable("id") int id,
											Model model) {
				RentalStatus rentalStatus = rentalStatusService.get(id);
				model.addAttribute("rentalStatus", rentalStatus);
			 return "/settings/rentalStatusEdit";
		}
		// save
		@PostMapping("/settings/rentalStatusUpdate")
		public String updateRentalStatus(@ModelAttribute("rentalStatus") @Valid RentalStatus rentalStatus,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/settings/rentalStatusEdit/"+rentalStatus.getId();
				}
				rentalStatusService.update(rentalStatus.getId(), rentalStatus);
				message = "RentalStatus updated successfully";
				return "redirect:/settings/rentalStatuses";
		}		
		@GetMapping("/settings/rentalStatusNew")
		public String rentalStatusForm(Locale locale, Model model) {

				model.addAttribute("rentalStatus", new RentalStatus());
				return "/settings/rentalStatusNew";
   }

		// save
		@PostMapping("/settings/rentalStatusSave")
   public String saveRentalStatus(@ModelAttribute("rentalStatus") @Valid RentalStatus rentalStatus,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/settings/rentalStatusNew";
      }
			message = "Rental Status saved successfully";
      rentalStatusService.save(rentalStatus);
      return "redirect:/settings/rentalStatuses";
   }

}
