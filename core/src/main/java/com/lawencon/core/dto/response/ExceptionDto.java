package com.lawencon.core.dto.response;

public class ExceptionDto<T>{
	T message;
	public T getMessage() {
		return message;
	}

	public void setMessage(T message) {
		this.message = message;
	}
}
