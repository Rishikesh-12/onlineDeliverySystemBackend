package com.cognizant.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cognizant.dto.LoginRequest;
import com.cognizant.dto.SignupRequest;
import com.cognizant.exception.SignupException;
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
	public boolean signup(SignupRequest signupRequest) throws SignupException {

		Optional<User> us = userRepository.findByUsername(signupRequest.getUsername());

		if (us.isPresent()) {
			log.info("User with " + signupRequest.getUsername() + " already exists");
			return false;
		} else {
			User user = new User();
			user.setId(0);
			user.setUsername(signupRequest.getUsername());
			user.setPassword(signupRequest.getPassword());
			user.setEmail(signupRequest.getEmail());
			user.setAddress(signupRequest.getAddress());
			user.setMobileno(signupRequest.getMobileno());
			userRepository.save(user);
			log.info("User saved");
			return true;
		}
	}

	public boolean login(LoginRequest loginRequest) {
		Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
		if (user.isPresent()) {
			if (user.get().getPassword().equals(loginRequest.getPassword())) {
				log.info("User with name " + loginRequest.getUsername() + " is present");
				return true;
			}
		}
		log.info("user with name " + loginRequest.getUsername() + " not present");
		return false;
	}
}
