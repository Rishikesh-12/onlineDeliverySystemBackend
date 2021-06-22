package com.cognizant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dto.LoginRequest;
import com.cognizant.dto.SignupRequest;
import com.cognizant.exception.SignupException;
import com.cognizant.model.MenuItem;
import com.cognizant.model.User;
import com.cognizant.service.MenuService;
import com.cognizant.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class HomeController {

	private final MenuService menuService;
	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) throws SignupException {
		boolean creationStatus = userService.signup(signupRequest);
		if (creationStatus)
			return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
		else
			return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/login")
	public boolean login(@RequestBody LoginRequest loginRequest) {
		boolean flag = userService.login(loginRequest);
		return flag;
	}

	@GetMapping("/menu")
	public ResponseEntity<List<MenuItem>> menu() {
		List<MenuItem> menuList = menuService.getMenuList();
		return new ResponseEntity<>(menuList, HttpStatus.OK);
	}

	@PostMapping("/menu")
	public ResponseEntity<String> addMenuItem(@RequestBody MenuItem menuItem) {
		menuService.addMenuItem(menuItem);
		return new ResponseEntity<>("Menu item added", HttpStatus.CREATED);
	}
}
