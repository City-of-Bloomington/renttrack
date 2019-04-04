package in.bloomington.rental.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import in.bloomington.rental.model.InspectionType;
import in.bloomington.rental.service.InspectionTypeService;


@Controller
public class InspectionTypeController {

		String message = null;
		
		@Autowired
		private InspectionTypeService inspectionTypeService;
		
		@GetMapping("/settings/inspectionTypes")
		public String inspectionTypeView(Locale locale, Model model) {
				
				model.addAttribute("inspectionTypes", inspectionTypeService.list());
				if(message != null)
						model.addAttribute("message", message);
				return "/settings/inspectionTypes";
		}
		// edit by id
		@GetMapping("/settings/inspectionType/{id}")
		public String editInspectionType(@PathVariable("id") int id,
											Model model) {
				InspectionType inspectionType = inspectionTypeService.get(id);
				model.addAttribute("inspectionType", inspectionType);
			 return "/settings/inspectionTypeEdit";
		}
		// save
		@PostMapping("/settings/inspectionTypeUpdate")
		public String updateInspectionType(@ModelAttribute("inspectionType") @Valid InspectionType inspectionType,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/settings/inspectionTypeEdit/"+inspectionType.getId();
				}
				inspectionTypeService.update(inspectionType.getId(), inspectionType);
				message = "InspectionType updated successfully";
				return "redirect:/settings/inspectionTypes";
		}		
		@GetMapping("/settings/newInspectionType")
		public String inspectionTypeForm(Locale locale, Model model) {

				model.addAttribute("inspectionType", new InspectionType());
				return "/settings/inspectionTypeForm";
   }

		// save
		@PostMapping("/settings/inspectionTypeSave")
   public String saveInspectionType(@ModelAttribute("inspectionType") @Valid InspectionType inspectionType,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/settings/newInspectionType";
      }
			message = "Rental Status saved successfully";
      inspectionTypeService.save(inspectionType);
      return "redirect:/settings/inspectionTypes";
   }

}
