package com.lawencon.core.dto.orderdtl;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrderDtlInsertReqDto {

    @NotBlank
    private String product;
    @NotNull
    private Integer quantity;
    @NotNull
    private Boolean isActive;
    @NotNull
    private Integer ver;
    
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
