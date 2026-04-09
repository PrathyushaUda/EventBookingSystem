package com.SpringFrame.EventBookingSystem.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringFrame.EventBookingSystem.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	// For login (future use)
 Optional<User> findByEmail(String email);
}
