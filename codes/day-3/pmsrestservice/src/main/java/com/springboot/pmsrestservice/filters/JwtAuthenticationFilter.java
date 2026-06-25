package com.springboot.pmsrestservice.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.pmsrestservice.services.AppUsersDetailsService;
import com.springboot.pmsrestservice.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;

	@Autowired
	ApplicationContext appContext;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = null;
		String username = null;

		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			token = header.substring(7);
			username = jwtService.extractUsername(token);
		}

		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authInfo = context.getAuthentication();

		if (username != null && authInfo == null) {
			UserDetails authUser = appContext.getBean(AppUsersDetailsService.class).loadUserByUsername(username);
			boolean isValid = jwtService.verifyToken(token, authUser);
			if (isValid) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authUser, null,
						authUser.getAuthorities());
				
				// it builds the details object from an HttpServletRequest object,
				// creating a WebAuthenticationDetails .

				WebAuthenticationDetailsSource detailsBuilder = new WebAuthenticationDetailsSource();
				WebAuthenticationDetails details = detailsBuilder.buildDetails(request);
				authToken.setDetails(details);
				context.setAuthentication(authToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
