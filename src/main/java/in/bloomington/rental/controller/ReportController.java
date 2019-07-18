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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import in.bloomington.rental.model.RentUser;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.model.InspectionType;
import in.bloomington.rental.model.BuildingType;
import in.bloomington.rental.model.PropertyType;
import in.bloomington.rental.model.PullReason;
import in.bloomington.rental.service.BuildingTypeService;
import in.bloomington.rental.service.InspectionTypeService;
import in.bloomington.rental.service.PropertyTypeService;
import in.bloomington.rental.service.PullReasonService;
import in.bloomington.rental.service.ReportService;

import in.bloomington.rental.util.ReportInspection;
import in.bloomington.rental.util.ReportRental;


@Controller
@Scope("session")
public class ReportController {

		String message = null;
																							
		@Autowired
		private ReportService reportService;
		@Autowired
    private InspectionTypeService inspectionTypeService;
		@Autowired		
		private BuildingTypeService buildingTypeService;
		@Autowired		
		private PropertyTypeService propertyTypeService;		
		@Autowired		
		private PullReasonService pullReasonService;
		
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
				System.err.println(" new report ");
				model.addAttribute("report", report);
				List<InspectionType> inspectionTypes = inspectionTypeService.list();
				System.err.println(" inspection types "+inspectionTypes);
				if(inspectionTypes != null)
						model.addAttribute("inspectionTypes", inspectionTypes);
				List<BuildingType> buildingTypes = buildingTypeService.list();
				System.err.println(" building types "+buildingTypes);				
				if(buildingTypes != null)
						model.addAttribute("buildingTypes", buildingTypes);
				return "reportInspection";
		}
		@PostMapping("/reportInspection")
		public String reportInspectionRun(@ModelAttribute("report") @Valid ReportInspection report, Model model) {
				String[] resultTitles = {"Inspection ID","Rental ID","Address","Building Type",
																 "Inspection Date","Inspection Type","Compliance Date","Violations","Smoke Detectors","Life Safety","Inspected By"};
				List<Object[]> results = reportService.getInspectionReport(report);
				if(results != null && results.size() > 0){
						model.addAttribute("results", results);
						model.addAttribute("reportTitle", "Inspection Report");
						model.addAttribute("resultTitles", resultTitles);
						return "reportResults";
				}
				else{
						message = "No match found";
						return "redirect:/reportInspection";
				}
		}				
		@GetMapping("/reportRental")
		public String reportRental(Locale locale,
															 Model model){
				if(message != null)
						model.addAttribute("message", message);
				ReportRental report = new ReportRental();
				model.addAttribute("report", report);
				List<PullReason> pullReasons = pullReasonService.list();
				if(pullReasons != null)
						model.addAttribute("pullReasons", pullReasons);
				List<BuildingType> buildingTypes = buildingTypeService.list();
				if(buildingTypes != null)
						model.addAttribute("buildingTypes", buildingTypes);
				List<PropertyType> propertyTypes = propertyTypeService.list();
				if(propertyTypes != null)
						model.addAttribute("propertyTypes", propertyTypes);				
				return "reportRental";
		}		
		@PostMapping("/reportRental")
		public String reportRentalRun(@ModelAttribute("report") @Valid ReportRental report, Model model) {
				String[] resultTitles = {"ID","Registered Date","Expire Date","Address","Owners","Owner Address","Owner City","Owner State","Owner Zip","Agent","Agent Address","Agent Zip","Buidling Type","Property Type","Buiding #","Units #","Bedrooms","Occupant Load","Efficiency?","Sleeping Room"};
				List<Object[]> results = reportService.getRentalReport(report);
				if(results != null && results.size() > 0){
						model.addAttribute("results", results);
						model.addAttribute("reportTitle", "Rental Report");
						model.addAttribute("resultTitles", resultTitles);
						return "reportResults";
				}
				else{
						message = "No match found";						
						model.addAttribute("message", message);						
						List<PullReason> pullReasons = pullReasonService.list();
						if(pullReasons != null)
								model.addAttribute("pullReasons", pullReasons);
						List<BuildingType> buildingTypes = buildingTypeService.list();
						if(buildingTypes != null)
								model.addAttribute("buildingTypes", buildingTypes);
						List<PropertyType> propertyTypes = propertyTypeService.list();
						if(propertyTypes != null)
								model.addAttribute("propertyTypes", propertyTypes);
						return "reportRental";
				}
		}				
}
