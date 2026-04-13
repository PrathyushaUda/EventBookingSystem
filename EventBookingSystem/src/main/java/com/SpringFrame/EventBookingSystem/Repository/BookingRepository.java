package com.SpringFrame.EventBookingSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SpringFrame.EventBookingSystem.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUserId(Long userId);

	List<Booking> findByEventId(Long eventId);

	
	@Query("SELECT b.event.title, COUNT(b) as total FROM Booking b GROUP BY b.event.title ORDER BY total DESC")
	List<Object[]> getTopSellingEvent();
	
	@Query("SELECT b.event.title, COUNT(b) FROM Booking b GROUP BY b.event.title")
	List<Object[]> getBookingCountByEvent();

	@Query("SELECT b.event.title, SUM(b.totalAmount) FROM Booking b GROUP BY b.event.title")
	List<Object[]> getRevenueByEvent();

	@Query("SELECT b.user.name, COUNT(b) FROM Booking b GROUP BY b.user.name")
	List<Object[]> getBookingsByUser();

}
