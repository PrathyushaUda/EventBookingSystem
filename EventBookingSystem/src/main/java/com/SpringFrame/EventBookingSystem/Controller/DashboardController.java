package com.SpringFrame.EventBookingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.SpringFrame.EventBookingSystem.Service.BookingService;
@Controller
public class DashboardController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("bookingCount",
                bookingService.getBookingCountByEvent());

        model.addAttribute("revenue",
                bookingService.getRevenueByEvent());

        model.addAttribute("userBookings",
                bookingService.getBookingsByUserReport());

        return "dashboard";
    }
}
