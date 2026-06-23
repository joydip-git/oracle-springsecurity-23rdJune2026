package com.security.basicapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
				.authorizeHttpRequests((req) -> req.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults())
				.build();
	}
	
	@Bean
	UserDetailsService useDetailsService(PasswordEncoder encoder) {
		UserBuilder userBuilder = User.builder();
		userBuilder.username("joy");
		userBuilder.password(encoder.encode("j@123"));
		userBuilder.roles("USER");
		
		UserDetails user1 = userBuilder.build();
		UserDetails user2 = User
				.builder()
				.username("anil")
				.password(encoder.encode("a@123"))
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user1, user2);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
