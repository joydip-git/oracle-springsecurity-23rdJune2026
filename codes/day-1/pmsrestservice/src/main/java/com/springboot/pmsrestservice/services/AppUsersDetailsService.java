package com.springboot.pmsrestservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.pmsrestservice.dtos.AppUserPrincipal;
import com.springboot.pmsrestservice.entities.AppUsers;
import com.springboot.pmsrestservice.repository.AppUsersRepository;

@Service
public class AppUsersDetailsService implements UserDetailsService {

	@Autowired
	private AppUsersRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUsers found = repository.findByUsername(username);
		
		if(found ==null) {
			System.out.println("user not found...");
			throw new UsernameNotFoundException(username);
		}
		return new AppUserPrincipal(found);
	}

}
