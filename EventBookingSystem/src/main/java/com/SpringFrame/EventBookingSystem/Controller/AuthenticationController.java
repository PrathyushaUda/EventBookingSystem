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

import jakarta.servlet.http.HttpSession;



@Controller
public class AuthenticationController {
	@Autowired
	private UserService userService;
	@GetMapping("/index")
		public String home() {
			return "index";
		}
	

	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email,
	                    @RequestParam String password,
	                    Model model,
	                    HttpSession session) {

	    User user = userService.findByEmail(email);

	    if (user != null && user.getPassword().equals(password)) {

	        // ✅ VERY IMPORTANT
	        session.setAttribute("user", user);

	        if (user.getRole().equals("ADMIN")) {
	            return "redirect:/dashboard";
	        }
	        return "redirect:/events";
	    }

	    model.addAttribute("error", "Invalid credentials");
	    return "login";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {

	    session.invalidate(); // 🔥 destroy session

	    return "redirect:/login";
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
