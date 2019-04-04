package in.bloomington.rental.controller;

import java.util.Locale;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import in.bloomington.rental.model.BuildingType;
import in.bloomington.rental.service.BuildingTypeService;


@Controller
public class BuildingTypeController {

		String message = null;
		
		@Autowired
		private BuildingTypeService buildingTypeService;
		
		@GetMapping("/settings/buildingTypes")
		public String buildingTypesView(Locale locale, Model model) {
				
				model.addAttribute("buildingTypes", buildingTypeService.list());
				if(message != null)
						model.addAttribute("message", message);
				return "/settings/buildingTypes";
		}
		// edit by id
		@GetMapping("/settings/buildingType/{id}")
		public String editBuildingType(@PathVariable("id") int id,
											Model model) {
				BuildingType buildingType = buildingTypeService.get(id);
				model.addAttribute("buildingType", buildingType);
			 return "/settings/buildingTypeEdit";
		}
		// save
		@PostMapping("/settings/buildingTypeUpdate")
		public String updateBuildingType(@ModelAttribute("buildingType") @Valid BuildingType buildingType,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/settings/buildingTypeEdit/"+buildingType.getId();
				}
				buildingTypeService.update(buildingType.getId(), buildingType);
				message = "BuildingType updated successfully";
				return "redirect:/settings/buildingTypes";
		}		
		@GetMapping("/settings/newBuildingType")
		public String buildingTypeForm(Locale locale, Model model) {

				model.addAttribute("buildingType", new BuildingType());
				return "/settings/buildingTypeForm";
   }

		// save
		@PostMapping("/settings/buildingTypeSave")
   public String saveBuildingType(@ModelAttribute("buildingType") @Valid BuildingType buildingType,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/settings/newBuildingType";
      }
			message = "BuildingType saved successfully";
      buildingTypeService.save(buildingType);
      return "redirect:/settings/buildingTypes";
   }

}
