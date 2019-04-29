package in.bloomington.rental.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

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
import in.bloomington.rental.service.UserService;
import in.bloomington.rental.util.GeneralHelper;

@Controller
@Scope("session")
public class LoginController {

		String message = null;

		@Autowired
		private RentUser user;
		
		@Autowired
		private UserService userService;

		@Autowired
		private GeneralHelper ghelper;

		@Autowired
		private HttpSession session;
		//
		@GetMapping("/")
		public String genenaral(Locale locale,
														Model model
														) {
				return "login";
		}
		/*
		// for CAS
		@PostMapping("/login")
		public String afterLogin2(ModelMap modelMap) {
				Authentication auth = SecurityContextHolder.getContext()
						.getAuthentication();
				if(auth != null
					 && auth.getPrincipal() != null
					 && auth.getPrincipal() instanceof UserDetails) {
						modelMap.put("username", ((UserDetails) auth.getPrincipal()).getUsername());
				}
				return "intro";
		}		
		*/		
		//
		@GetMapping("/login")
		public String startLogin(Locale locale,
													 Model model) {
				if(message != null){
						model.addAttribute("message", message);
						message = null;
				}
				return "login";
		}
		// 
		// login verify
		@PostMapping("/loginVerify")
		public String dologin(@ModelAttribute("user") RentUser user2,
													BindingResult result,
													Model model
													// HttpSession session
													) {
				boolean found = false;
				user = null;
				if(user2 != null){
						String username = user2.getUsername();
						String password = user2.getPassword();
						System.err.println(" username "+username);
						if(username == null || username.trim().equals("")){
								message = "username is required ";
								return "redirect:/login";
						}
						if(password == null || password.trim().equals("")){
								message = "password is required ";
								return "redirect:/login";
						}
						if(ghelper != null){
								if(ghelper.getLdapHost().equals(""))
										ghelper.populatePaths();
								found = ghelper.findUser(username, password);
						}
						if(!found){
								message = "username or password not recognized, try again ";
								return "redirect:/login";
						}
						user = userService.findByUsername(username);
						System.err.println(" login user "+user);
						if(user != null && session != null){
								session.setAttribute("user",user);
								message = "User login successfully";
								model.addAttribute("message", message);
								return "intro";						
						}
						message = "User not found try again";
						return "redirect:/login";							
				}
				else{
						message = "User not found try again";
						return "redirect:/login";						
				}
		}
		/** for CAS
		@GetMapping("/logout")
		public String logout(HttpServletRequest request, 
												 HttpServletResponse response, 
												 SecurityContextLogoutHandler logoutHandler) {
				Authentication auth = SecurityContextHolder
						.getContext().getAuthentication();
				logoutHandler.logout(request, response, auth );
				new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY)
						.logout(request, response, auth);
				return "successLogout";
		}
		*/
		// 
		@GetMapping("/logout")
		public String logout(Model model,
												 HttpSession session
												 ) {
				
				RentUser user = null;
				if(session != null){
						user = (RentUser)session.getAttribute("user");
						if(user != null){
								System.err.println(" logout user "+user);
								session.invalidate();
								message = "User logout successfully";								
						}
				}
				return "redirect:/login";
		}
		@GetMapping("/showErrors/{msg}")
		public String showErrors(@PathVariable("msg") String msg,
													 Model model
													 ) {
				model.addAttribute("message", msg);
				return "errors";
		}				

}


