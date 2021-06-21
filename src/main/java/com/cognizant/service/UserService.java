package com.cognizant.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cognizant.dto.LoginRequest;
import com.cognizant.model.User;
import com.cognizant.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	@Transactional
	public void signup(User user) {
		userRepository.save(user);
		log.info("User saved");
	}

	public boolean login(LoginRequest loginRequest) {
		Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
		if(user.isPresent()) {
			if(user.get().getPassword().equals(loginRequest.getPassword()) ) {
				log.info("User with name "+ loginRequest.getUsername()  +" is present");
				return true;
			}
		}
		log.info("user with name "+ loginRequest.getUsername()  +" not present");
		return false;
	}
}
