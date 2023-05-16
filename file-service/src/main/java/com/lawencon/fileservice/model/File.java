package com.lawencon.fileservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.core.model.BaseEntity;

@Entity
@Table(name = "files")
public class File extends BaseEntity {

	private static final long serialVersionUID = 8619069861957750645L;

	@Column(name = "encode", nullable = false, columnDefinition = "text")
	private String encode;

	@Column(name = "extension", nullable = false)
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
