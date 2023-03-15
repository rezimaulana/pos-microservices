package com.lawencon.core.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.lawencon.core.pojo.AuthorizationPojo;

@Component
public class AuthenticationUtil {

	public AuthorizationPojo getPrincipal() {
		final var authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			final AuthorizationPojo authorizationPojo = (AuthorizationPojo) authentication.getPrincipal();
			return authorizationPojo;
		}
		return null;
	}

}

