package com.lawencon.core.dto.file;

public class FileDataDto {
    
	private String id;
    private String encode;
    private String extension;
    private Boolean isActive;
    private Integer ver;
    
    public String getId() {
    	return id;
    }
    public void setId(String id) {
    	this.id = id;
    }
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
    public Boolean getIsActive() {
    	return isActive;
    }
    public void setIsActive(Boolean isActive) {
    	this.isActive = isActive;
    }
    public Integer getVer() {
    	return ver;
    }
    public void setVer(Integer ver) {
    	this.ver = ver;
    }

}
