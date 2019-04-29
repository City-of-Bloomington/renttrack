package in.bloomington.rental.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import in.bloomington.rental.model.BuildingType;
import in.bloomington.rental.model.PropertyType;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.RentalStructure;
import in.bloomington.rental.service.BuildingTypeService;
import in.bloomington.rental.service.PropertyTypeService;
import in.bloomington.rental.service.RentalService;
import in.bloomington.rental.service.RentalStructureService;


@Controller
public class StructureController {

		String message = null;
		final static String[] foundationTypes = {"Basement","Slab","Cellar","Crawl Space","Other"};
		final static String[] heatSources = {"Electric","Gas","Electric/Gas","Other"};				
		@Autowired
		private RentalStructureService structureService;
		@Autowired
		private RentalService rentalService;
		@Autowired
		private PropertyTypeService propertyTypeService;
		@Autowired
		private BuildingTypeService buildingTypeService;		
		static List<BuildingType> buildingTypes = null;
		static List<PropertyType> propertyTypes = null;
		
		@GetMapping("/structures/{rental_id}")
		public String structuresView(@PathVariable("rental_id") int rental_id, Model model) {
				
				model.addAttribute("structures", structureService.findByRentalId(rental_id));
				if(message != null)
						model.addAttribute("message", message);
				return "structures";
		}
		// get by id
		@GetMapping("/structure/{id}")
		public String getStructure(@PathVariable("id") int id,
											Model model) {
				RentalStructure structure = structureService.get(id);
				model.addAttribute("structure", structure);
				if(message != null){
						model.addAttribute("message", message);
						message = null;
				}
				return "structureView";
		}
		// edit by id
		@GetMapping("/structureEdit/{id}")
		public String editStructure(@PathVariable("id") int id,
											Model model) {
				RentalStructure structure = structureService.get(id);
				model.addAttribute("structure", structure);
				if(buildingTypes == null){
						buildingTypes = buildingTypeService.list();
				}
				if(buildingTypes != null){
						model.addAttribute("buildingTypes", buildingTypes);
				}
				if(propertyTypes == null){
						propertyTypes = propertyTypeService.list();
				}
				if(propertyTypes != null){
						model.addAttribute("propertyTypes", propertyTypes);
				}
				model.addAttribute("foundationTypes", foundationTypes);
				model.addAttribute("heatSources",heatSources);				
			 return "structureEdit";
		}
		// get by id
		@GetMapping("/structureDelete/{id}")
		public String deleteStructure(@PathVariable("id") int id) {
				System.err.println(" id "+id);
				RentalStructure structure = structureService.get(id);
				int rentalId = structure.getRentalId();
				structureService.delete(structure);
				message = "Structure deleted successfully";
				return "redirect:/"+rentalId;
		}		
		// save
		@PostMapping("/structureUpdate")
		public String updateStructure(@ModelAttribute("structure") @Valid RentalStructure structure,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/structureEdit/"+structure.getId();
				}
				structureService.update(structure.getId(), structure);
				message = "Structure updated successfully";
				return "redirect:/structure/"+structure.getId();
		}		
		@GetMapping("/structureNew/{id}")
		public String structureForm(
															@PathVariable("id") int id,Model model) {
				RentalStructure structure = new RentalStructure();
				Rental rental = rentalService.get(id);
				int structCnt = rental.getStructureCount()+1;
				structure.setIdentifier(""+structCnt);// new identified based on cnt
				structure.setRental(rental);
				model.addAttribute("structure", structure);
				if(buildingTypes == null){
						buildingTypes = buildingTypeService.list();
				}
				if(buildingTypes != null){
						model.addAttribute("buildingTypes", buildingTypes);
				}
				if(propertyTypes == null){
						propertyTypes = propertyTypeService.list();
				}
				if(propertyTypes != null){
						model.addAttribute("propertyTypes", propertyTypes);
				}
				model.addAttribute("foundationTypes", foundationTypes);
				model.addAttribute("heatSources",heatSources);				
				return "structureNew";
		}
		// save
		@PostMapping("/structureSave")
   public String saveStructure(@ModelAttribute("structure") @Valid RentalStructure structure,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/structureNew";
      }
      structureService.save(structure);
			int id = structure.getId();
			message = "Structure saved successfully";
			return "redirect:/structure/"+id; 
   }

}
