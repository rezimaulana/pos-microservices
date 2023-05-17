package com.lawencon.core.dto.response;

import java.util.List;

public class DataListResDto<T> {
    
    private List<T> data;

	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
