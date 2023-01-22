package com.trackerforce.queue.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${service.endpoint.allowed-addresses}")
	protected String[] allowedAddresses;

	@Value("${service.endpoint.allowed-endpoints}")
	protected String[] allowedEndpoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers(allowedEndpoint).permitAll()
				.antMatchers("/**").access(buildAllowedIpList())
				.anyRequest().authenticated()

				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

				.and()
				.cors()

				.and()
				.csrf().disable();
	}

	private String buildAllowedIpList() {
		final String accessIpAddress = Arrays.stream(allowedAddresses)
				.map(address -> "hasIpAddress('" + address.trim() + "') or ")
				.collect(Collectors.joining());

		return accessIpAddress.substring(0, accessIpAddress.length() - 4);
	}

}