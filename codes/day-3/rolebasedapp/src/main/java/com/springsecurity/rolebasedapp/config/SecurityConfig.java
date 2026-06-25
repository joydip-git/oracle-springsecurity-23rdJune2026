package com.springsecurity.rolebasedapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf((customizer) -> customizer.disable())
				.authorizeHttpRequests(
						(req) -> req
							.requestMatchers(HttpMethod.GET, "/")
							.hasAnyRole("USER", "ADMIN")
							.requestMatchers(HttpMethod.POST, "/")
							.hasRole("ADMIN")
							.anyRequest().authenticated()
						)
				.httpBasic(Customizer.withDefaults())
				.build();
	}

	@Bean
	UserDetailsService useDetailsService(PasswordEncoder encoder) {
		UserBuilder userBuilder = User.builder();

		UserDetails user1 = userBuilder.username("joy").password(encoder.encode("j@123")).roles("USER").build();

		UserDetails user2 = User.builder().username("anil").password(encoder.encode("a@123")).roles("ADMIN").build();

		return new InMemoryUserDetailsManager(user1, user2);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
