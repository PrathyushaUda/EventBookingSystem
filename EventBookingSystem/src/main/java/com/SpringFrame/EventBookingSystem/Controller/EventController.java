package com.SpringFrame.EventBookingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SpringFrame.EventBookingSystem.Repository.UserRepository;
import com.SpringFrame.EventBookingSystem.Service.EventService;
import com.SpringFrame.EventBookingSystem.model.Event;
import com.SpringFrame.EventBookingSystem.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class EventController {
	@Autowired
	private EventService eventService;
	@Autowired 
	private UserRepository userRepository;

	@GetMapping("/events")
	public String getAllEvents(Model model,HttpSession session) {
		 User user = (User) session.getAttribute("user"); // ✅ dynamic user

		    model.addAttribute("loggedInUser", user);

		    model.addAttribute("events", eventService.getAllEvents());
		return "events";
	}
	@GetMapping("/add-event")
	public String showAddEvent(Model model) {
		model.addAttribute("event",new Event());
		return "add-event";
	}
	@PostMapping("/add-event")
	public String addEvent(@ModelAttribute Event event){
		eventService.saveEvent(event);
		return "redirect:/events";
		
	}
	 @GetMapping("/delete-event/{id}")
	    public String showDeletePage(@PathVariable Long id, Model model, HttpSession session) {

	        User user = (User) session.getAttribute("user");

	        if (user == null || !user.getRole().equals("ADMIN")) {
	            return "redirect:/login";
	        }

	        model.addAttribute("event", eventService.getEventById(id));

	        return "delete-event";
	    }

	    // 🔥 DELETE EVENT (POST)
	    @PostMapping("/delete-event")
	    public String deleteEvent(@RequestParam Long id, HttpSession session) {

	        User user = (User) session.getAttribute("user");

	        if (user == null || !user.getRole().equals("ADMIN")) {
	            return "redirect:/login";
	        }

	        eventService.deleteEvent(id);

	        return "redirect:/events";
	    }
	}

