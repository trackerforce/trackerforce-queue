package com.trackerforce.queue.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtTokenService {

	private static final Map<String, Object> DEFAULT_CLAIMS = Map.of("roles", List.of("INTERNAL"));

	private final int jwtTokenExpire;

	private final String jwtSecret;

	public JwtTokenService(@Value("${service.jwt.expire}") int jwtTokenExpire,
						   @Value("${service.jwt.secret}") String jwtSecret) {
		this.jwtTokenExpire = jwtTokenExpire * 60 * 1000;
		this.jwtSecret = jwtSecret;
	}

	public String generateToken(String subject, String organizationAlias) {
		final var key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
		final var claimsBuild = Jwts.claims()
				.expiration(new Date(System.currentTimeMillis() + jwtTokenExpire))
				.audience().add(organizationAlias)
				.and()
				.add(DEFAULT_CLAIMS)
				.subject(subject).build();

		return Jwts.builder().claims(claimsBuild).signWith(key).compact();
	}
	
}