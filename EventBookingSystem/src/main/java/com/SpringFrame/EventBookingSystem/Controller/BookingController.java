package com.SpringFrame.EventBookingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SpringFrame.EventBookingSystem.Service.BookingService;
import com.SpringFrame.EventBookingSystem.Service.EmailService;
import com.SpringFrame.EventBookingSystem.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookingController {
	@Autowired
	private BookingService bookingService;
	@Autowired
	private EmailService emailService;
	@PostMapping("/book")
	public String bookTickets(@RequestParam Long eventId,
	                          @RequestParam int tickets,
	                          HttpSession session,
	                          Model model) {

	    try {
	        User user = (User) session.getAttribute("user");

	        bookingService.bookTickets(eventId, user.getId(), tickets);

	        return "redirect:/events";

	    } catch (Exception e) {
	        model.addAttribute("error", e.getMessage());
	        return "error";
	    }
	
}

// 🔹 My bookings page
	@GetMapping("/my-bookings")
	public String myBookings(HttpSession session, Model model) {

	    User user = (User) session.getAttribute("user");

	    if (user == null) {
	        return "redirect:/login";
	    }

	    model.addAttribute("bookings",
	        bookingService.getBookingsByUser(user.getId())
	    );

	    return "my-bookings";
	}
	
}
