package com.SpringFrame.EventBookingSystem.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringFrame.EventBookingSystem.model.Event;

@Service
public interface EventService {
	List<Event> getAllEvents();
	List<String> getSoldOutEvents();

	Event getEventById(Long id);

	Event saveEvent(Event event);

	void deleteEvent(Long id);
}
