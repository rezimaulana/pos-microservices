package com.lawencon.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class CoreApplication {
    
    private final KeyPair key = Keys.keyPairFor(SignatureAlgorithm.RS256);
	
    public static void main(String[] args) {
		final CoreApplication coreApplication = new CoreApplication();

        RSAPublicKey publicKey = (RSAPublicKey) coreApplication.key.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) coreApplication.key.getPrivate();
        
        System.out.println("Public key:");
        System.out.println(coreApplication.getPem(publicKey));
        
        System.out.println("Private key:");
        System.out.println(coreApplication.getPem(privateKey));
	}

    private String getPem(Object key) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(baos);
        try {
            if (key instanceof RSAPublicKey) {
                RSAPublicKey publicKey = (RSAPublicKey) key;
                osw.write("-----BEGIN PUBLIC KEY-----\n");
                osw.write(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
                osw.write("\n-----END PUBLIC KEY-----");
            } else if (key instanceof RSAPrivateKey) {
                RSAPrivateKey privateKey = (RSAPrivateKey) key;
                osw.write("-----BEGIN PRIVATE KEY-----\n");
                osw.write(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
                osw.write("\n-----END PRIVATE KEY-----");
            } else {
                throw new IllegalArgumentException("Key type not supported: " + key.getClass().getSimpleName());
            }
            osw.flush();
            return new String(baos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to get PEM string for key", e);
        }
    }

}