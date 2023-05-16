package com.lawencon.core.dto.file;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FileInsertReqDto {

	@NotBlank
	private String encode;
	@NotNull
	private String extension;
    
    public String getEncode() {
		return encode;
	}
	public void setEncode(String encode) {
		this.encode = encode;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}

}
