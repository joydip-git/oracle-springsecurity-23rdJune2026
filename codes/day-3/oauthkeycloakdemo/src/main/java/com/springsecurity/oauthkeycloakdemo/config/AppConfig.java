package com.springsecurity.oauthkeycloakdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;

@Configuration
public class AppConfig {

	@Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
	private String issuerUri;
	
	@Bean
	JwtDecoder jwtDecoder() {
		return JwtDecoders.fromIssuerLocation(issuerUri);
	}
}
