package com.security.basicapp.config;

import com.security.basicapp.controller.HelloController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final HelloController helloController;

    SecurityConfig(HelloController helloController) {
        this.helloController = helloController;
    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// Allows restricting access based upon the HttpServletRequest using
		// RequestMatcher implementations (i.e. via URL patterns).
		//AuthorizationManagerRequestMatcherRegistry req
		//http.authorizeHttpRequests(req -> req.requestMatchers("/*").authenticated());
		//CsrfConfigurer<HttpSecurity>
		return http
			.csrf((CsrfConfigurer<HttpSecurity> customizer) -> customizer.disable())
			.authorizeHttpRequests((req) -> req.anyRequest().authenticated())
			.formLogin(Customizer.withDefaults())
			.httpBasic(Customizer.withDefaults())
			.build();
	}
	
//	@Bean
//	public AuthenticationManager authenticationManager() {
//		
//	}
//	
//	@Bean
//	public UserDetailsService useDetailsService() {}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		
//	}
//	
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		return new DaoAuthenticationProvider();
//	}
}
