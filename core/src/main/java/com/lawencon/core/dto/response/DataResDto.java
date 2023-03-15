package com.lawencon.core.dto.response;

public class DataResDto<T> {
    
    private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
