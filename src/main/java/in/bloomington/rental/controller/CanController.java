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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.json.JSONObject;
import org.json.JSONArray;
import in.bloomington.rental.model.Can;
import in.bloomington.rental.service.CanService;

@Controller
public class CanController {

		String message = null;
		
		@Autowired
		private CanService canService;
		
		@GetMapping("/settings/cans")
		public String canView(Locale locale, Model model) {
				
				model.addAttribute("cans", canService.getAll());
				if(message != null)
						model.addAttribute("message", message);
				return "/settings/cans";
		}
		// edit by id
		@GetMapping("/settings/can/{id}")
		public String editCan(@PathVariable("id") int id,
											Model model) {
				Can can = canService.get(id);
				model.addAttribute("can", can);
			 return "/settings/can";
		}
		// save
		@PostMapping("/settings/canUpdate")
		public String updateCan(@ModelAttribute("can") @Valid Can can,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/settings/can/"+can.getId();
				}
				canService.update(can.getId(), can);
				message = "can updated successfully";
				return "redirect:/settings/cans";
		}		
		@GetMapping("/settings/canNew")
		public String canForm(Locale locale, Model model) {

				model.addAttribute("can", new Can());
				return "/settings/canNew";
   }

		// save
		@PostMapping("/settings/canSave")
   public String saveCan(@ModelAttribute("can") @Valid Can can,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/settings/canNew";
      }
			message = "Can saved successfully";
      canService.save(can);
      return "redirect:/settings/cans";
   }
}
@RestController		
class CanServiceController {
		
		@Autowired
		private CanService canService;
		@GetMapping(value="/canService", produces = "application/json")
		public String canService(@RequestParam("term") String term,
															 Locale locale, Model model) {
				String json = "";
				if(term != null && term.length() > 2){
						List<Can> cans = canService.findByName(term);
						if(cans != null && cans.size() > 0){
								json = buildJson(cans);
								// System.err.println(json);
						}
				}
				return json;
		}
		private String buildJson(List<Can> cans){
				String json = "";
				JSONArray jArr = new JSONArray();
				for(Can one:cans){
						JSONObject jObj = new JSONObject();
						jObj.put("id", one.getId());
						jObj.put("value",one.getTitle());
						jObj.put("type",one.getType());
						jObj.put("item1",one.getItem1());
						jObj.put("item2",one.getItem2());
						jObj.put("item3",one.getItem3());
						jObj.put("item4",one.getItem4());
						jObj.put("item5",one.getItem5());
						jObj.put("item6",one.getItem6());
						jObj.put("item7",one.getItem7());
						jObj.put("item8",one.getItem8());
						jArr.put(jObj);
				}
				json = jArr.toString();;
				return json;
		}		

}
