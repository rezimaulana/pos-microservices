package com.lawencon.core.dto.response;

import java.util.List;

public class DataListResDto<T> {
    
    private List<T> data;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
