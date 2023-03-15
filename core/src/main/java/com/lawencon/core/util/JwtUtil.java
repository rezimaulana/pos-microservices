package com.lawencon.core.util;

import java.security.KeyPair;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final KeyPair key = Keys.keyPairFor(SignatureAlgorithm.RS256);
	
	public String generateJwt(Map<String, Object> claims) {
		final String jwt = Jwts.builder().setClaims(claims).signWith(key.getPrivate()).compact();
		return jwt;
	}

	public Map<String, Object> parseJwt(String token) {
		return Jwts.parserBuilder().setSigningKey(key.getPublic()).build().parseClaimsJws(token).getBody();
	}
	
}
