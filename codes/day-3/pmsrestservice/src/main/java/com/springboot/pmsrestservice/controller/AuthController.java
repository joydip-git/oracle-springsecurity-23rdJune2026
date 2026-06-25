package com.springboot.pmsrestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pmsrestservice.dtos.AppUserRequest;
import com.springboot.pmsrestservice.dtos.AppUserResponse;
import com.springboot.pmsrestservice.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public AppUserResponse register(@RequestBody AppUserRequest user) {
		return authService.createUser(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody AppUserRequest user) {
		return authService.verify(user);
	}
}
