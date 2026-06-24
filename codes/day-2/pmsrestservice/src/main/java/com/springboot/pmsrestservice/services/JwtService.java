package com.springboot.pmsrestservice.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private String secretKey = "poVTv0qx1VizUJwew5aKiL115kSC3FQt86VQbqX6NLb";

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();

	 	SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
	 	
		JwtBuilder jwtBuilder = Jwts.builder();
		String token = jwtBuilder
			.claims()
			.add(claims)
			.subject(username)
			.issuedAt(new Date(System.currentTimeMillis()))
			.expiration(new Date(System.currentTimeMillis() + (60 * 60 * 60 * 24)))
			.and()
			.signWith(key)
			.compact();
		
		return token;
	}

	public String extractUsername(String token) {		
		return "name";
	}

	public boolean verifyToken(String token, UserDetails authUser) {
		return true;
	}
}
