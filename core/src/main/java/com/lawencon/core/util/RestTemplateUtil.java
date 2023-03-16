package com.lawencon.core.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateUtil {

	private final RestTemplate restTemplate;
	private final AuthenticationUtil authenticationUtil;
	
	public RestTemplateUtil(RestTemplate restTemplate, AuthenticationUtil authenticationUtil) {
		this.restTemplate = restTemplate;
		this.authenticationUtil = authenticationUtil;
	}

	public <E> ResponseEntity<E> get(Class<E> entityType, String pathUrl, String baseURL) {
		return get(entityType, pathUrl, authenticationUtil.getPrincipal().getToken(), baseURL);
	}

	public <E> ResponseEntity<E> get(Class<E> entityType, String pathUrl, String token, String baseURL) {
		final var headers = new HttpHeaders();
		headers.setBearerAuth(token);

		final var url = baseURL + pathUrl;

		final var req = RequestEntity.get(url).headers(headers).build();
		return restTemplate.exchange(req, entityType);
	}

	public <E> ResponseEntity<E> post(Class<E> entityType, String pathUrl, E body, String baseURL) {
		return post(entityType, pathUrl, body, authenticationUtil.getPrincipal().getToken(), baseURL);
	}

	public <E> ResponseEntity<E> post(Class<E> entityType, String pathUrl, E body, String token, String baseURL) {
		final var headers = new HttpHeaders();
		headers.setBearerAuth(token);

		final var url = baseURL + "/" + pathUrl;

		RequestEntity<?> req = null;
		if (body != null) {
			req = RequestEntity.post(url).headers(headers).body(body);
		} else {
			req = RequestEntity.post(url).headers(headers).build();
		}

		return restTemplate.exchange(req, entityType);
	}

	public <E> ResponseEntity<E> verify(Class<E> entityType, String token, String baseURL) {
		return post(entityType, "verify", null, token, baseURL);
	}
}