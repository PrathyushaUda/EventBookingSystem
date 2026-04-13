package com.SpringFrame.EventBookingSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SpringFrame.EventBookingSystem.model.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
	@Query("SELECT e.title FROM Event e WHERE e.availableSeats = 0")
	List<String> getSoldOutEvents();
}
