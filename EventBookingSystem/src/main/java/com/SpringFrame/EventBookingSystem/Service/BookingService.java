package com.SpringFrame.EventBookingSystem.Service;

import java.util.List;

import com.SpringFrame.EventBookingSystem.model.Booking;

public interface BookingService {
	 Booking bookTickets(Long eventId, Long userId, int tickets);

	    List<Booking> getBookingsByUser(Long userId);

	    List<Booking> getBookingsByEvent(Long eventId);

	    List<Object[]> getBookingCountByEvent();

	    List<Object[]> getRevenueByEvent();

	    List<Object[]> getBookingsByUserReport();
}
