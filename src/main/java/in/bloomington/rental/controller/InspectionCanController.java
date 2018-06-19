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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import in.bloomington.rental.model.InspectionCan;
import in.bloomington.rental.service.InspectionCanService;
import in.bloomington.rental.model.Inspection;
import in.bloomington.rental.service.InspectionService;
import in.bloomington.rental.model.TemplateComponent;
import in.bloomington.rental.service.TemplateComponentService;
import in.bloomington.rental.model.InspectionTemplate;
import in.bloomington.rental.service.InspectionTemplateService;

@Controller
public class InspectionCanController {

		String message = null;
		
		@Autowired
		private InspectionCanService inspectionCanService;
		@Autowired
		private InspectionService inspectionService;		

		@Autowired
		private InspectionTemplateService inspectionTemplateService;
		@Autowired
		private TemplateComponentService componentService;		

		// edit by id
		@GetMapping("/inspectionCan/{id}")
		public String editInspectionCan(@PathVariable("id") int id,
											Model model) {
				InspectionCan inspectionCan = inspectionCanService.get(id);
				model.addAttribute("inspectionCan", inspectionCan);
			 return "/inspectionCan";
		}
		@GetMapping("/inspectionCanRemove/{id}")
		public String removeInspectionCan(@PathVariable("id") int id,
											Model model) {
				InspectionCan inspectionCan = inspectionCanService.get(id);
				int inspection_id = inspectionCan.getInspection().getId();
				inspectionCanService.delete(id);
			 return "redirect:/inspection/"+inspection_id;
		}		
		// update
		@PostMapping("/inspectionCanUpdate")
		public String updateInspectionCan(@ModelAttribute("inspectionCan") @Valid InspectionCan inspectionCan,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/inspectionCan/"+inspectionCan.getId();
				}
				inspectionCanService.update(inspectionCan.getId(), inspectionCan);
				int id = inspectionCan.getInspection().getId();
				message = "can updated successfully";
				return "redirect:/inspectionCanNew/"+id;
		}
		@GetMapping("/inspectionCanNew/{id}")
		public String inspectionCanNew(@PathVariable("id") int id,
																		Model model) {
				Inspection inspection = inspectionService.get(id);
				if(inspection.requireTemplate()){
						int rid = inspection.getRental().getId();
						List<InspectionTemplate> list = inspectionTemplateService.findByRentalId(rid);
						if(list != null && list.size() > 0){
								inspection.setInspectionTemplate(list.get(0));
						}
				}
				model.addAttribute("inspection", inspection);
				if(message != null){
						model.addAttribute("message", message);
						message = null;
				}
				return "/componentCans";
   }
		// id = inspection id
		// cid = component id
		@GetMapping("/inspectionCanAdd/{id}/{cid}")
		public String inspectionCanAdd(@PathVariable("id") int id,
																	 @PathVariable("cid") int cid,
																		Model model) {
				TemplateComponent component = componentService.get(cid);
				InspectionCan inspectionCan = new InspectionCan();
				Inspection inspection = inspectionService.get(id); 
				inspectionCan.setTemplateComponent(component);
				inspectionCan.setInspection(inspection);				
				model.addAttribute("inspectionCan", inspectionCan);
				return "/inspectionCanNew";
   }
		// id = inspection id

		// save
		@PostMapping("/inspectionCanSave")
		public String saveInspectionCan(@ModelAttribute("inspectionCan") @Valid InspectionCan inspectionCan,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/inspectionCanNew";
      }
			message = " Saved successfully";
      inspectionCanService.save(inspectionCan);
      return "redirect:/inspectionCanNew/"+inspectionCan.getInspection().getId();
   }
		// update
}


