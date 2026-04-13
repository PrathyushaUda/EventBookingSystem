package com.SpringFrame.EventBookingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.SpringFrame.EventBookingSystem.Service.BookingService;
import com.SpringFrame.EventBookingSystem.Service.EventService;
import com.SpringFrame.EventBookingSystem.model.User;

import jakarta.servlet.http.HttpSession;
@Controller
public class DashboardController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private EventService eventService;
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {

        // ✅ Get logged-in user
        User user = (User) session.getAttribute("user");

        // ❌ If not logged in
        if (user == null) {
            return "redirect:/login";
        }

        // ❌ If not ADMIN → block access
        if (!user.getRole().equals("ADMIN")) {
            return "redirect:/events";
        }

        // ✅ Only ADMIN can access below

        model.addAttribute("bookingCount",
                bookingService.getBookingCountByEvent());

        model.addAttribute("revenue",
                bookingService.getRevenueByEvent());

        model.addAttribute("topEvent",
                bookingService.getTopSellingEvent());

        model.addAttribute("soldOutEvents",
                eventService.getSoldOutEvents());

        model.addAttribute("userBookings",
                bookingService.getBookingsByUserReport());

        return "dashboard";
    }
   
}
