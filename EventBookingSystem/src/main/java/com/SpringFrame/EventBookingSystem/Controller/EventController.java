package com.SpringFrame.EventBookingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.SpringFrame.EventBookingSystem.Service.EventService;
import com.SpringFrame.EventBookingSystem.model.Event;

@Controller
public class EventController {
	@Autowired
	private EventService eventService;

	@GetMapping("/events")
	public String getAllEvents(Model model) {
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
}
