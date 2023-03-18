package com.lawencon.core.util;

import org.springframework.core.ParameterizedTypeReference;
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

	public <E> ResponseEntity<E> get(ParameterizedTypeReference<E> entityTypeRef, String pathUrl, String baseURL) {
		return get(entityTypeRef, pathUrl, authenticationUtil.getPrincipal().getToken(), baseURL);
	}
	
	public <E> ResponseEntity<E> get(ParameterizedTypeReference<E> entityTypeRef, String pathUrl, String token, String baseURL) {
		final var headers = new HttpHeaders();
		headers.setBearerAuth(token);
	
		final var url = baseURL + pathUrl;
	
		final var req = RequestEntity.get(url).headers(headers).build();
		return restTemplate.exchange(req, entityTypeRef);
	}	

	public <E> ResponseEntity<E> post(ParameterizedTypeReference<E> entityTypeRef, String pathUrl, E body, String baseURL) {
		return post(entityTypeRef, pathUrl, body, authenticationUtil.getPrincipal().getToken(), baseURL);
	}

	public <E> ResponseEntity<E> post(ParameterizedTypeReference<E> entityTypeRef, String pathUrl, E body, String token, String baseURL) {
		final var headers = new HttpHeaders();
		headers.setBearerAuth(token);

		final var url = baseURL + pathUrl;

		RequestEntity<?> req = null;
		if (body != null) {
			req = RequestEntity.post(url).headers(headers).body(body);
		} else {
			req = RequestEntity.post(url).headers(headers).build();
		}

		return restTemplate.exchange(req, entityTypeRef);
	}

	public <E> ResponseEntity<E> put(ParameterizedTypeReference<E> entityTypeRef, String pathUrl, E body, String baseURL) {
		return put(entityTypeRef, pathUrl, body, authenticationUtil.getPrincipal().getToken(), baseURL);
	}

	public <E> ResponseEntity<E> put(ParameterizedTypeReference<E> entityTypeRef, String pathUrl, E body, String token, String baseURL) {
		final var headers = new HttpHeaders();
		headers.setBearerAuth(token);

		final var url = baseURL + pathUrl;

		RequestEntity<?> req = null;
		if (body != null) {
			req = RequestEntity.put(url).headers(headers).body(body);
		} else {
			req = RequestEntity.put(url).headers(headers).build();
		}

		return restTemplate.exchange(req, entityTypeRef);
	}

	public ResponseEntity<Void> delete(String pathUrl, String baseURL) {
		return delete(pathUrl, authenticationUtil.getPrincipal().getToken(), baseURL);
	}

	public ResponseEntity<Void> delete(String pathUrl, String token, String baseURL) {
		final var headers = new HttpHeaders();
		headers.setBearerAuth(token);

		final var url = baseURL + pathUrl;

		final var req = RequestEntity.delete(url).headers(headers).build();
		return restTemplate.exchange(req, Void.class);
	}

	public <E> ResponseEntity<E> verify(ParameterizedTypeReference<E> entityTypeRef, String token, String baseURL) {
		return post(entityTypeRef, "verify", null, token, baseURL);
	}
}
