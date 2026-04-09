package com.SpringFrame.EventBookingSystem.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringFrame.EventBookingSystem.Repository.BookingRepository;
import com.SpringFrame.EventBookingSystem.Repository.EventRepository;
import com.SpringFrame.EventBookingSystem.Repository.UserRepository;
import com.SpringFrame.EventBookingSystem.model.Booking;
import com.SpringFrame.EventBookingSystem.model.Event;
import com.SpringFrame.EventBookingSystem.model.User;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Booking bookTickets(Long eventId, Long userId, int tickets) {

		Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));

		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		if (tickets <= 0) {
			throw new RuntimeException("Invalid ticket count");
		}

		if (event.getAvailableSeats() < tickets) {
			throw new RuntimeException("Not enough seats available");
		}

		if (event.getDate().isBefore(LocalDate.now())) {
			throw new RuntimeException("Event already completed");
		}

		event.setAvailableSeats(event.getAvailableSeats() - tickets);
		eventRepository.save(event);

		Booking booking = new Booking();
		booking.setEvent(event);
		booking.setUser(user);
		booking.setNumberOfTickets(tickets);
		booking.setTotalAmount(event.getPrice() * tickets);
		booking.setBookingDate(LocalDate.now());
		booking.setStatus("CONFIRMED");

		return bookingRepository.save(booking);
	}

	@Override
	public List<Booking> getBookingsByUser(Long userId) {

		return bookingRepository.findByUserId(userId);
	}

	@Override
	public List<Booking> getBookingsByEvent(Long eventId) {

		return bookingRepository.findByEventId(eventId);
	}

	@Override
	public List<Object[]> getBookingCountByEvent() {

		return bookingRepository.getBookingCountByEvent();
	}

	@Override
	public List<Object[]> getRevenueByEvent() {

		return bookingRepository.getRevenueByEvent();
	}

	@Override
	public List<Object[]> getBookingsByUserReport() {

		return bookingRepository.getBookingsByUser();
	}

}
