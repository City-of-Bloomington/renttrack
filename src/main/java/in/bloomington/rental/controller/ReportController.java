package in.bloomington.rental.controller;

import java.util.Locale;
import java.util.List;

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
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.model.InspectionType;
import in.bloomington.rental.model.BuildingType;
import in.bloomington.rental.service.BuildingTypeService;
import in.bloomington.rental.service.InspectionTypeService;
import in.bloomington.rental.service.ReportService;

import in.bloomington.rental.util.ReportInspection;

@Controller
@Scope("session")
public class ReportController {

		String message = null;
																							
		@Autowired
		private ReportService reportService;
		@Autowired
    private InspectionTypeService inspectionTypeService;
		private BuildingTypeService buildingTypeService;		
		

		@Autowired
		HttpSession session;
		@GetMapping("/reports")
		public String reportView(Locale locale, Model model) {
				
				if(message != null)
						model.addAttribute("message", message);
				reportService.getAll();
				return "reports";
		}
		@GetMapping("/reportInspection")
		public String reportInspect(Locale locale, Model model) {
				
				if(message != null)
						model.addAttribute("message", message);
				ReportInspection report = new ReportInspection();
				model.addAttribute("report", report);
				List<InspectionType> inspectionTypes = inspectionTypeService.list();
				if(inspectionTypes != null)
						model.addAttribute("inspectionTypes", inspectionTypes);
				List<BuildingType> buildingTypes = buildingTypeService.list();
				if(buildingTypes != null)
						model.addAttribute("buildingTypes", buildingTypes);
				return "report_inspection_form";
		}		
		

}
