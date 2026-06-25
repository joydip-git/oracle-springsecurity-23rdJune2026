package com.springboot.pmsrestservice.dtos;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.pmsrestservice.entities.AppUser;

public class AppUserPrincipal implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AppUser user;

	public AppUserPrincipal(AppUser user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		SimpleGrantedAuthority auth = new SimpleGrantedAuthority(user.getRole());
		SimpleGrantedAuthority auth = new SimpleGrantedAuthority("USER");
		return Collections.singleton(auth);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

}
