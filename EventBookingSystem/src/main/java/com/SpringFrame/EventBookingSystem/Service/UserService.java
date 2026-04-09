package com.SpringFrame.EventBookingSystem.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.SpringFrame.EventBookingSystem.model.User;

@Service
public interface UserService {
	User saveUser(User user);

	User getUserById(Long id);

	User findByEmail(String email);
	User registerUser(User user);
}
