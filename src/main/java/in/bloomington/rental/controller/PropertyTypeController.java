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

import in.bloomington.rental.model.PropertyType;
import in.bloomington.rental.service.PropertyTypeService;


@Controller
public class PropertyTypeController {

		String message = null;
		
		@Autowired
		private PropertyTypeService propertyTypeService;
		
		@GetMapping("/settings/propertyTypes")
		public String propertyTypesView(Locale locale, Model model) {
				
				model.addAttribute("propertyTypes", propertyTypeService.list());
				if(message != null)
						model.addAttribute("message", message);
				return "/settings/propertyTypes";
		}
		// edit by id
		@GetMapping("/settings/propertyType/{id}")
		public String editPropertyType(@PathVariable("id") int id,
											Model model) {
				PropertyType propertyType = propertyTypeService.get(id);
				model.addAttribute("propertyType", propertyType);
			 return "/settings/propertyTypeEdit";
		}
		// save
		@PostMapping("/settings/propertyTypeUpdate")
		public String updatePropertyType(@ModelAttribute("propertyType") @Valid PropertyType propertyType,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/settings/propertyTypeEdit/"+propertyType.getId();
				}
				propertyTypeService.update(propertyType.getId(), propertyType);
				message = "PropertyType updated successfully";
				return "redirect:/settings/propertyTypes";
		}		
		@GetMapping("/settings/newPropertyType")
		public String propertyTypeForm(Locale locale, Model model) {

				model.addAttribute("propertyType", new PropertyType());
				return "/settings/propertyTypeForm";
   }

		// save
		@PostMapping("/settings/propertyTypeSave")
   public String savePropertyType(@ModelAttribute("propertyType") @Valid PropertyType propertyType,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/settings/newPropertyType";
      }
			message = "PropertyType saved successfully";
      propertyTypeService.save(propertyType);
      return "redirect:/settings/propertyTypes";
   }

}
