package com.lawencon.core.util;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtParseUtil {
	
	private PublicKey decodedPublicKey;
	
	public JwtParseUtil(@Value("${public.key}") String publicKey) {
		// Decode private and public key from Base64 encoded string
		byte[] decodedPublicKeyBytes = Base64.getDecoder().decode(publicKey.getBytes(StandardCharsets.UTF_8));
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			decodedPublicKey = keyFactory.generatePublic(new X509EncodedKeySpec(decodedPublicKeyBytes));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException("Error decoding keys", e);
		}
	}

	public Map<String, Object> parseJwt(String token) {
		// return Jwts.parserBuilder().setSigningKey(key.getPublic()).build().parseClaimsJws(token).getBody();
		return Jwts.parserBuilder().setSigningKey(decodedPublicKey).build().parseClaimsJws(token).getBody();
	}

}
