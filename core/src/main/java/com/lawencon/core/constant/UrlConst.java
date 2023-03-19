package com.lawencon.core.constant;

public enum UrlConst {
	FRONTEND_BASE("http://localhost:3001"),
    GATEWAY_BASE("http://gateway-service"),
    GATEWAY_USER_GET_BY_ID("/users?id="),
	GATEWAY_PRODUCT_GET_BY_ID("/products?id="),
	GATEWAY_PRODUCT_UPDATE("/products");

	private final String uri;
	
	UrlConst(final String uri) {
		this.uri = uri;
	}
	
	public String getUri() {
		return uri;
	}
}
