package com.SpringFrame.EventBookingSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringFrame.EventBookingSystem.Repository.UserRepository;
import com.SpringFrame.EventBookingSystem.model.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {

		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {

		return userRepository.findById(id).orElseThrow();
	}

	@Override
	public  User findByEmail(String email) {

		return userRepository.findByEmail(email).orElseThrow();
	}

	@Override
	public User registerUser(User user) {
		 if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			 
			
		        throw new RuntimeException("Email already exists");
		    }

		    user.setRole("USER");
		return userRepository.save(user);
	}

}
