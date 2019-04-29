package in.bloomington.rental.controller;

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

import in.bloomington.rental.model.RentUser;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.RentalNote;
import in.bloomington.rental.service.RentalNoteService;
import in.bloomington.rental.service.RentalService;

@Controller
public class RentalNoteController {

		String message = null;
		
		@Autowired
		private RentalNoteService noteService;
		@Autowired
		private RentalService rentalService;		
		@Autowired
		private HttpSession session;
		//
		@GetMapping("/notes/{rental_id}")
		public String notesView(@PathVariable("rental_id") int rental_id,
														Model model) {
				
				model.addAttribute("notes", noteService.findByRentalId(rental_id));
				if(message != null)
						model.addAttribute("message", message);
				return "notes";
		}
		// get by id
		@GetMapping("/note/{id}")
		public String getNote(@PathVariable("id") int id,
											Model model) {
				RentalNote note = noteService.get(id);
				model.addAttribute("note", note);
			 return "noteView";
		}

		/*
		// edit by id
		@GetMapping("/noteEdit/{id}")
		public String editNote(@PathVariable("id") int id,
											Model model) {
				RentalNote note = noteService.get(id);
				model.addAttribute("note", note);
			 return "noteEdit";
		}
			
			// these activities are not allowed for notes
		// delete
		@GetMapping("/noteDelete/{id}")
		public String deleteVariance(@PathVariable("id") int id) {
				System.err.println(" id "+id);
				noteService.delete(id);
				message = "Note deleted successfully";
			 return "redirect:/notes";
		}		
		// save
		@PostMapping("/noteUpdate")
		public String updateNote(@ModelAttribute("note") @Valid RentalNote note,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/noteEdit/"+note.getId();
				}
				noteService.update(note.getId(), note);
				message = "Note updated successfully";
				return "redirect:/"+note.getRental().getId();
		}
		*/
		@GetMapping("/noteNew/{rental_id}")
		public String noteForm(@PathVariable("rental_id") int rental_id,Model model) {
				Rental rental = rentalService.get(rental_id);
				RentalNote note = new RentalNote();
				note.setRental(rental);
				model.addAttribute("note", note);
				return "noteNew";
   }

		// save
		@PostMapping("/noteSave")
   public String saveNote(@ModelAttribute("note") @Valid RentalNote note,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/noteNew";
      }
			RentUser user = null;
			user = (RentUser)session.getAttribute("user");
			note.setUser(user);
			noteService.save(note);
			Rental rental = note.getRental();
			message = "Note saved successfully";
			return "redirect:/view/"+rental.getId(); 					
   }

}
