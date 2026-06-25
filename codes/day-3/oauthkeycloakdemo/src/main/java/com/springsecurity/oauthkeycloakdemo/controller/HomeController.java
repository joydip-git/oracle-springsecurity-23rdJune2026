package com.springsecurity.oauthkeycloakdemo.controller;

import java.util.Collection;

import com.springsecurity.oauthkeycloakdemo.config.SecurityConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final SecurityConfig securityConfig;

    HomeController(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }

	@GetMapping("/home")
	public String home(@AuthenticationPrincipal OidcUser principal, 
			@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client ,
			Model model) {

		model.addAttribute("username", principal.getPreferredUsername());
		model.addAttribute("name",principal.getFullName());
		model.addAttribute("email",principal.getEmail());
		model.addAttribute("roles",principal.getAuthorities());
		
		System.out.println(client.getAccessToken().getTokenValue());
		System.out.println(principal.getIdToken().getTokenValue());
		return "home";
	}

	@GetMapping("/user")
	public String user() {
		return "user";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	@GetMapping("/manager")
	public String manager() {
		return "manager";
	}
}
