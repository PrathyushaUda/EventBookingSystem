package com.SpringFrame.EventBookingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.SpringFrame.EventBookingSystem.Service.OtpService;
import com.SpringFrame.EventBookingSystem.Service.UserService;
import com.SpringFrame.EventBookingSystem.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthenticationController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserService userService;

    // 🔹 Home
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    // 🔹 Login Page
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    // 🔹 Normal Login (Email + Password)
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {

        User user = userService.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {

            session.setAttribute("user", user);

            if (user.getRole().equals("ADMIN")) {
                return "redirect:/dashboard";
            }
            return "redirect:/events";
        }

        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    // 🔥 SEND EMAIL OTP
    @PostMapping("/send-email-otp")
    public String sendEmailOtp(@RequestParam String email, Model model) {


        otpService.sendEmailOtp(email);

        model.addAttribute("message", "OTP sent to your email!");
        model.addAttribute("email", email); // 🔥 REQUIRED

        return "login";
    }

    // 🔥 VERIFY EMAIL OTP
    @PostMapping("/verify-email-otp")
    public String verifyEmailOtp(@RequestParam String email,
                                @RequestParam String otp,
                                HttpSession session,
                                Model model) {

        boolean isValid = otpService.validateOtp(email, otp);

        if (!isValid) {
            model.addAttribute("error", "Invalid OTP");
            return "login";
        }

        User user = userService.findByEmail(email);

        if (user == null) {
            model.addAttribute("error", "User not found");
            return "login";
        }

        // ✅ Login success
        session.setAttribute("user", user);

        return "redirect:/events";
    }

    // 🔹 Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // 🔹 Register Page
    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // 🔹 Register User
    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed");
            return "register";
        }
    }
}