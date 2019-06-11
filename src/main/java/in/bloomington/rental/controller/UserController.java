package in.bloomington.rental.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import in.bloomington.rental.model.RentUser;
import in.bloomington.rental.service.UserService;
import in.bloomington.rental.util.CommonParam;


@Controller
public class UserController {

		String message = null;
		
		@Autowired
		private UserService userService;

		@GetMapping("/users")
		public String usersView(Locale locale, Model model) {
				
				model.addAttribute("users", userService.list());
				if(message != null)
						model.addAttribute("message", message);
				return "/settings/users";
		}
		// get by id
		@GetMapping("/user/{id}")
		public String getUser(@PathVariable("id") int id,
											Model model) {
				System.err.println(" id "+id);
				RentUser user = userService.get(id);
				model.addAttribute("user", user);
			 return "/settings/userView";
		}
		// edit by id
		@GetMapping("/userEdit/{id}")
		public String editUser(@PathVariable("id") int id,
											Model model) {
				System.err.println(" id "+id);
				RentUser user = userService.get(id);
				model.addAttribute("user", user);
				model.addAttribute("roles", CommonParam.roles);
			 return "/settings/userEdit";
		}
		// get by id
		@GetMapping("/userDelete/{id}")
		public String deleteUser(@PathVariable("id") int id) {
				System.err.println(" id "+id);
				userService.delete(id);
				message = "User deleted successfully";
			 return "redirect:/users";
		}		
		// save
		@PostMapping("/userUpdate")
		public String updateUser(@ModelAttribute("user") @Valid RentUser user,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						handleErrors(result);
						return "redirect:/userEdit/"+user.getId();
				}
				userService.update(user.getId(), user);
				message = "User updated successfully";
				return "redirect:/users";
		}		
		@GetMapping("/userNew")
		public String userForm(Locale locale, Model model) {

				model.addAttribute("user", new RentUser());
				model.addAttribute("roles", CommonParam.roles);
				if(message != null)
						model.addAttribute("message", message);
				message = null;
				return "/settings/userNew";
   }

		// save
		@PostMapping("/userSave")
   public String saveUser(@ModelAttribute("user") @Valid RentUser user,
         BindingResult result, Model model) {
				if (result.hasErrors()) {
						handleErrors(result);
						return "redirect:/userNew";
				}
				message = "User saved successfully";
				userService.save(user);
				return "redirect:/settings/users";
		}
		private void handleErrors(BindingResult result){
				List<FieldError> errors = result.getFieldErrors();
				for (FieldError error : errors ) {
						message += error.getObjectName() + " - " + error.getDefaultMessage()+" - "+error.getRejectedValue()+"<br />";		
				}
		}
}
