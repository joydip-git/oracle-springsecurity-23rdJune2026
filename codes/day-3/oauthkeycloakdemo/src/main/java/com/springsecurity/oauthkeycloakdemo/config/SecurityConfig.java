package com.springsecurity.oauthkeycloakdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.springsecurity.oauthkeycloakdemo.services.CustomOidcUserService;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

	@Autowired
	private CustomOidcUserService oidcUserService;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests(req->
			req
			.requestMatchers("/login","/error").permitAll()
			.requestMatchers("/user").hasRole("USER")
			.requestMatchers("/amdin").hasRole("ADMIN")
			.requestMatchers("/manager").hasRole("MANAGER")
			.anyRequest().authenticated())
			.oauth2Login(
					oauth->oauth.loginPage("/login")
					.defaultSuccessUrl("/home",true)
					.userInfoEndpoint(endpoint -> endpoint.oidcUserService(oidcUserService))
					);
		
		return http.build();
	}
}
