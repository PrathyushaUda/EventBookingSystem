package com.SpringFrame.EventBookingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SpringFrame.EventBookingSystem.Service.UserService;
import com.SpringFrame.EventBookingSystem.model.User;



@Controller
public class AuthenticationController {
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, Model model) {
		User user = userService.findByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			if(user.getRole().equals("ADMIN")) {
				return "redirect:/add-event";
			}
			return "redirect:/events";
		}

		model.addAttribute("error", "Invalid credentials");
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute User user, Model model){
		 try {
	            userService.registerUser(user);
	            return "redirect:/login";
	        } catch (Exception e) {
		return "register";
	}
}
}
