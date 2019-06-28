package in.bloomington.rental.controller;

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

import in.bloomington.rental.model.RentUser;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.Variance;
import in.bloomington.rental.service.RentalService;
import in.bloomington.rental.service.VarianceService;

@Controller
@Scope("session")
public class ReportController {

		String message = null;
		
		//@Autowired
		// private RentalService rentalService;		

		@Autowired
		HttpSession session;
		@GetMapping("/reports")
		public String variancesView(Locale locale, Model model) {
				
				if(message != null)
						model.addAttribute("message", message);
				return "reports";
		}

}
