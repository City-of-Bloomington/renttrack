package in.bloomington.rental.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import in.bloomington.rental.model.Address;
import in.bloomington.rental.model.Inspection;
import in.bloomington.rental.model.RentUser;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.RentalLog;
import in.bloomington.rental.model.RentalStatus;
import in.bloomington.rental.model.Zoning;
import in.bloomington.rental.service.AddressService;
import in.bloomington.rental.service.InspectionService;
import in.bloomington.rental.service.RentalLogService;
import in.bloomington.rental.service.RentalService;
import in.bloomington.rental.service.RentalStatusService;
import in.bloomington.rental.service.ZoningService;
import in.bloomington.rental.util.Helper;
import in.bloomington.rental.util.CommonParam;

@Controller
@Scope("session")
public class RentalController {

		String message = null;
		static List<Zoning> zonings = null;
		static List<RentalStatus> statuses = null;
		List<Inspection> inspections = null;
		@Autowired
		private RentalService rentalService;
		
		@Autowired
		private RentalStatusService statusService;
		
		@Autowired
		private ZoningService zoningService;
		
		@Autowired
		private RentalLogService logService;
		
		@Autowired
		private InspectionService inspectionService;

		@Autowired
		private AddressService addressService;

		@Autowired
		private HttpSession session;		

		@GetMapping("/rentals")
		public String rentalsView(Model model, Principal principal) {

				model.addAttribute("rentals", rentalService.getAll());
				if(message != null)
						model.addAttribute("message", message);
				return "rentals";
		}

		// get by id
		@GetMapping("/view/{id}")
		public String getRental(@PathVariable("id") int id,
														Model model) {
				Rental rental = rentalService.get(id);
				inspections = inspectionService.findByRentalId(id);
				if(inspections != null && inspections.size()> 0){
						rental.setInspections(inspections);
				}
				model.addAttribute("rental", rental);
				if(message != null)
						model.addAttribute("message", message);
				return "rentalView";
		}
		// edit by id
		@GetMapping("/edit/{id}")
		public String editRental(@PathVariable("id") int id,
											Model model) {
				Rental rental = rentalService.get(id);
				model.addAttribute("nhoods", CommonParam.nhoods);
				inspections = inspectionService.findByRentalId(id);
				if(inspections != null && inspections.size()> 0){
						rental.setInspections(inspections);
				}
				model.addAttribute("rental", rental);
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
			 return "rentalEdit";
		}
		// get by id
		@GetMapping("/delete/{id}")
		public String deleteRental(@PathVariable("id") int id) {
				System.err.println(" id "+id);
				rentalService.delete(id);
				message = "Rental deleted successfully";
			 return "redirect:/rentals";
		}		
		// save
		@PostMapping("/update")
		public String updateRental(@ModelAttribute("rental") @Valid Rental rental,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/edit/"+rental.getId();
				}
				RentUser user = null;
				if(session != null){
						user = (RentUser)session.getAttribute("user");
				}				
				rentalService.update(rental.getId(), rental);
				RentalLog log = new RentalLog();
				log.setRental(rental);
				log.setUser(user);
				log.setActionTaken("Updated");
				log.setDate(new Date());
				logService.save(log);
				message = "Rental updated successfully";
				return "redirect:/view/"+rental.getId(); // to view
		}		
		@GetMapping("/new")
		public String rentalForm(Locale locale, Model model) {

				model.addAttribute("rental", new Rental());
				model.addAttribute("nhoods", CommonParam.nhoods);
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
				return "rentalNew";
   }
		// save
		@PostMapping("/save")
		public String saveRental(@ModelAttribute("rental") @Valid Rental rental,
														 BindingResult result, Model model) {
				System.err.println("in saving ");
				if (result.hasErrors()) {
						System.err.println("having errors in save");
						System.err.println(result);
						return "redirect:/new";
				}
				rentalService.save(rental);
				RentUser user = null;
				if(session != null){
						user = (RentUser)session.getAttribute("user");
				}
				RentalLog log = new RentalLog();
				log.setRental(rental);
				log.setUser(user);
				log.setActionTaken("Started");
				log.setDate(new Date());
				logService.save(log);				
				message = "Rental saved successfully";
				return "redirect:/edit/"+rental.getId();
		}
		@GetMapping("/permit/{id}")
		public String getPermit(@PathVariable("id") int id,
														Model model) {
				Rental rental = rentalService.get(id);
				//
				// last inspection with compliance date				
				Inspection inspection = null; 
				inspections = inspectionService.findByRentalId(id);
				List<Address> addresses = addressService.findByRentalId(id);
				if(addresses != null && addresses.size() > 0){
						rental.setAddresses(addresses);
				}
				//
				// already sorted by id desc
				// we want cycle inspection with compliance date
				//
				if(inspections != null && inspections.size()> 0){
						for(Inspection one:inspections){
								if(one.hasComplianceDate() && one.isCycleType()){
										inspection = one;
										break;
								}
						}
						if(inspection != null){
								model.addAttribute("inspection", inspection);
						}
				}
				model.addAttribute("rental", rental);
				model.addAttribute("today", Helper.getToday());
				if(message != null)
						model.addAttribute("message", message);
				return "permit";
		}
}
