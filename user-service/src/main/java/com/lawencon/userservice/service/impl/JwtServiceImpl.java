package com.lawencon.userservice.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lawencon.userservice.service.declaration.JwtService;

import io.jsonwebtoken.Jwts;

@Service
public class JwtServiceImpl implements JwtService {
    private final KeyPair key;
	
	public JwtServiceImpl(@Value("${private.key}") String privateKey, @Value("${public.key}") String publicKey) {
		// Decode private and public key from Base64 encoded string
		byte[] decodedPrivateKeyBytes = Base64.getDecoder().decode(privateKey.getBytes(StandardCharsets.UTF_8));
		byte[] decodedPublicKeyBytes = Base64.getDecoder().decode(publicKey.getBytes(StandardCharsets.UTF_8));
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey decodedPrivateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedPrivateKeyBytes));
			PublicKey decodedPublicKey = keyFactory.generatePublic(new X509EncodedKeySpec(decodedPublicKeyBytes));
			this.key = new KeyPair(decodedPublicKey, decodedPrivateKey);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException("Error decoding keys", e);
		}
	}
	
	
	public String generateJwt(Map<String, Object> claims) {
		final String jwt = Jwts.builder().setClaims(claims).signWith(key.getPrivate()).compact();
		return jwt;
	}

}
