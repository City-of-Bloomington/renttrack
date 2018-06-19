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

import in.bloomington.rental.model.Bill;
import in.bloomington.rental.service.BillService;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.service.RentalService;
import in.bloomington.rental.util.GeneralHelper;
import org.springframework.context.annotation.Scope;

@Controller
@Scope("session")
public class BillController {

		String message = null;
		
		@Autowired
		private BillService billService;
		@Autowired
		private GeneralHelper ghelper;
		@Autowired
		private RentalService rentalService;		

		@GetMapping("/bills")
		public String billsView(Locale locale, Model model) {
				
				model.addAttribute("bills", billService.getAll());
				if(message != null)
						model.addAttribute("message", message);
				return "bills";
		}
		// get by id
		@GetMapping("/billView/{id}")
		public String getBill(@PathVariable("id") int id,
											Model model) {
				Bill bill = billService.get(id);
				model.addAttribute("bill", bill);
				if(message != null)
						model.addAttribute("message", message);
				return "billView";
		}
		// edit by id
		@GetMapping("/billEdit/{id}")
		public String editBill(@PathVariable("id") int id,
											Model model) {
				Bill bill = billService.get(id);
				model.addAttribute("bill", bill);
			 return "billEdit";
		}
		// 
		@GetMapping("/billPrint/{id}")
		public String printBill(@PathVariable("id") int id,
											Model model) {
				Bill bill = billService.get(id);
				int rental_id = bill.getRental().getId();
				Rental rental = rentalService.get(rental_id);				
				model.addAttribute("bill", bill);
				model.addAttribute("rental", rental);				
			 return "billPrint";
		}		
		// get by id
		@GetMapping("/billDelete/{id}")
		public String deleteBill(@PathVariable("id") int id) {
				System.err.println(" id "+id);
				billService.delete(id);
				message = "Bill deleted successfully";
			 return "redirect:/bills";
		}		
		// save
		@PostMapping("/billUpdate")
		public String updateBill(@ModelAttribute("bill") @Valid Bill bill,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/billEdit/"+bill.getId();
				}
				billService.update(bill.getId(), bill);
				message = "Bill updated successfully";
				model.addAttribute("bill", bill);				
				return "billEdit"; 
		}
		// 
		@GetMapping("/billNew/{rental_id}")
		public String billForm(@PathVariable("rental_id") int rental_id, Locale locale, Model model) {
				Rental rental = rentalService.get(rental_id);
				Bill bill = new Bill();
				bill.setRental(rental);
				if(ghelper != null){
						ghelper.setBill(bill);
						String back = ghelper.populateRates();
						if(!back.equals("")){
								message = "Error setting rates ";
						}
				}
				bill.setRentalInfo(); // property type, #buildings, #units etc
				model.addAttribute("bill", bill);
				return "billNew";
   }
		// save
		@PostMapping("/billSave")
		public String saveBill(@ModelAttribute("bill") @Valid Bill bill,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						return "redirect:/billNew";
				}
				billService.save(bill);
				bill.getRental();
				model.addAttribute("bill", bill);				
				message = "Bill saved successfully";
				return "billEdit";
		}

}
