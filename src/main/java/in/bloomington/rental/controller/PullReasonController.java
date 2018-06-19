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
import org.springframework.http.ResponseEntity;

import in.bloomington.rental.model.PullReason;
import in.bloomington.rental.service.PullReasonService;


@Controller
public class PullReasonController {

		String message = null;
		
		@Autowired
		private PullReasonService pullReasonService;
		
		@GetMapping("/settings/pullReasons")
		public String pullReasonView(Locale locale, Model model) {
				
				model.addAttribute("pullReasons", pullReasonService.list());
				if(message != null)
						model.addAttribute("message", message);
				return "/settings/pullReasons";
		}
		// edit by id
		@GetMapping("/settings/pullReason/{id}")
		public String editPullReason(@PathVariable("id") int id,
											Model model) {
				PullReason pullReason = pullReasonService.get(id);
				model.addAttribute("pullReason", pullReason);
			 return "/settings/pullReasonEdit";
		}
		// save
		@PostMapping("/settings/pullReasonUpdate")
		public String updatePullReason(@ModelAttribute("pullReason") @Valid PullReason pullReason,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/settings/pullReasonEdit/"+pullReason.getId();
				}
				pullReasonService.update(pullReason.getId(), pullReason);
				message = "Pull Reason updated successfully";
				return "redirect:/settings/pullReasons";
		}		
		@GetMapping("/settings/pullReasonNew")
		public String pullReasonForm(Locale locale, Model model) {

				model.addAttribute("pullReason", new PullReason());
				return "/settings/pullReasonForm";
   }

		// save
		@PostMapping("/settings/pullReasonSave")
   public String savePullReason(@ModelAttribute("pullReason") @Valid PullReason pullReason,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/settings/pullReasonNew";
      }
			message = "Pull Reason saved successfully";
      pullReasonService.save(pullReason);
      return "redirect:/settings/pullReasons";
   }

}
