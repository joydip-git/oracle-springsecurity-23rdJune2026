package com.springsecurity.oauthkeycloakdemo.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService extends OidcUserService {

	@Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
	private String clientId;

	@Autowired
	private JwtDecoder jwtDecoder;

	@Override
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		OidcUser oidcUser = super.loadUser(userRequest);
		String accessToken = userRequest.getAccessToken().getTokenValue();
		Set<GrantedAuthority> roles = extractRoles(accessToken);
		roles.addAll(oidcUser.getAuthorities());
		return new DefaultOidcUser(roles, oidcUser.getIdToken(), oidcUser.getUserInfo());
	}

	private Set<GrantedAuthority> extractRoles(String accessToken) {
		Jwt jwt = jwtDecoder.decode(accessToken);
		Map<String, Object> resourceAccess = jwt.getClaim("resource_access");

		if (resourceAccess == null)
			return Collections.emptySet();

		Map<String, Object> clientResources = (Map<String, Object>) resourceAccess.get(clientId);

		if (resourceAccess == null)
			return Collections.emptySet();

		List<String> roles = (List<String>) clientResources.get("roles");

		if (roles == null || roles.isEmpty())
			return Collections.emptySet();

		return roles.stream().map((role) -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
				.collect(Collectors.toSet());
	}

}
