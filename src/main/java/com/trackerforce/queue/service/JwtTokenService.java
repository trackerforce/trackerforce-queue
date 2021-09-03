package com.trackerforce.queue.service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenService {

	@Value("${service.jwt.expire}")
	private int JWT_TOKEN_VALIDITY;

	@Value("${service.jwt.secret}")
	private String secret;
	
	public String generateToken(String subject, String organizationAlias) {
		final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
		final String token = Jwts.builder()
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 60 * 1000))
				.setSubject(subject)
				.setAudience(organizationAlias)
				.addClaims(getDefaultClaim())
				.signWith(key)
				.compact();
		
		return token;
	}
	
	private HashMap<String, Object> getDefaultClaim() {
		var claims = new HashMap<String, Object>();
		claims.put("roles", Arrays.asList("INTERNAL"));
		return claims;
	}
	
}