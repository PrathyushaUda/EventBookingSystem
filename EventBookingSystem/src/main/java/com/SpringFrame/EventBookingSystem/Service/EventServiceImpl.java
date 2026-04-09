package com.SpringFrame.EventBookingSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringFrame.EventBookingSystem.Repository.EventRepository;
import com.SpringFrame.EventBookingSystem.model.Event;

@Service
public class EventServiceImpl implements EventService {
	@Autowired
	private EventRepository eventRepository;

	@Override
	public List<Event> getAllEvents() {

		return eventRepository.findAll();
	}

	@Override
	public Event getEventById(Long id) {

		return eventRepository.findById(id).orElseThrow();
	}

	@Override
	public Event saveEvent(Event event) {

		return eventRepository.save(event);
	}

	@Override
	public void deleteEvent(Long id) {
		eventRepository.deleteById(id);

	}

}
