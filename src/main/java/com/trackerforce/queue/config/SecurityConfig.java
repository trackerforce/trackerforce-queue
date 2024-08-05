package com.trackerforce.queue.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

import java.util.function.Supplier;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Value("${service.endpoint.allowed-addresses}")
	protected String[] allowedAddresses;

	@Value("${service.endpoint.allowed-endpoints}")
	protected String[] allowedEndpoint;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth -> auth.requestMatchers(allowedEndpoint).permitAll()
						.anyRequest().access(this::authorize))
						.sessionManagement(auth -> auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
						.csrf(AbstractHttpConfigurer::disable).build();
	}

	private AuthorizationDecision authorize(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
		final var remoteAddress = object.getRequest().getRemoteAddr();
		var decision = new AuthorizationDecision(authentication.get().isAuthenticated());

		boolean isAllowed = false;
		for (String address : allowedAddresses) {
			IpAddressMatcher ipAddressMatcher = new IpAddressMatcher(address);
			if (ipAddressMatcher.matches(remoteAddress)) {
				isAllowed = true;
				break;
			}
		}

		if (!isAllowed) {
			decision = new AuthorizationDecision(false);
		}

		return decision;
	}

}