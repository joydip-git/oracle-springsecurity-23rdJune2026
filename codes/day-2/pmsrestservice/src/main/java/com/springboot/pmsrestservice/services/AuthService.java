package com.springboot.pmsrestservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.pmsrestservice.dtos.AppUserRequest;
import com.springboot.pmsrestservice.dtos.AppUserResponse;
import com.springboot.pmsrestservice.entities.AppUser;
import com.springboot.pmsrestservice.repository.AppUsersRepository;

@Service
public class AuthService {

	@Autowired
	private AppUsersRepository repository;

	// private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;

	private AppUser toEntity(AppUserRequest user) {
		AppUser entity = new AppUser();
		entity.setUsername(user.username());
		entity.setPassword(encoder.encode(user.password()));
		return entity;
	}

	private AppUserResponse toResponse(AppUser user) {
		return new AppUserResponse(user.getId(), user.getUsername(), user.getPassword());
	}

	public AppUserResponse createUser(AppUserRequest request) {
		AppUser entity = toEntity(request);
		AppUser added = repository.save(entity);
		return toResponse(added);
	}

	public String verify(AppUserRequest user) {
		Authentication unauthToken = new UsernamePasswordAuthenticationToken(user.username(), user.password());
		Authentication authToken = authManager.authenticate(unauthToken);
		if(authToken.isAuthenticated()) {
			return jwtService.generateToken(user.username());
		}else
		   throw new UsernameNotFoundException("user with "+ user.username()+" not found");
	}
}
