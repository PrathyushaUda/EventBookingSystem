package com.SpringFrame.EventBookingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SpringFrame.EventBookingSystem.Service.BookingService;

@Controller
public class BookingController {
	@Autowired
	private BookingService bookingService;

	@PostMapping("/book")
	public String bookTickets(@RequestParam Long eventId, @RequestParam Long userId, @RequestParam int tickets,
			Model model) {
		try {
			bookingService.bookTickets(eventId, userId, tickets);
			return "redirect:/events";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}
	}

// 🔹 My bookings
	@GetMapping("/my-bookings")
	public String myBookings(@RequestParam Long userId, Model model) {
		model.addAttribute("bookings", bookingService.getBookingsByUser(userId));
		return "my-bookings";
	}
}
