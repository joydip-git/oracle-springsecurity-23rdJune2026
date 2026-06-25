package com.springboot.pmsrestservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.springboot.pmsrestservice.dtos.AppUserPrincipal;
import com.springboot.pmsrestservice.entities.AppUser;
import com.springboot.pmsrestservice.repository.AppUsersRepository;

@Service
public class AppUsersDetailsService implements UserDetailsService {

	@Autowired
	private AppUsersRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<AppUsers> found = repository.findByUsername(username);		
//		found.orElseThrow(() -> new UsernameNotFoundException("usrer not found"));
		
//		if(found.isEmpty()) {
//			System.out.println("user not found...");
//			throw new UsernameNotFoundException(username);
//		}
		
//		return new AppUserPrincipal(found);
		
		AppUser found = repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("usrer not found"));
		UserDetails user = User
				.builder()
				.username(found.getUsername())
				.password(found.getPassword())
				.roles(found.getRole())
				.build();
		
		return user;
	}

}
