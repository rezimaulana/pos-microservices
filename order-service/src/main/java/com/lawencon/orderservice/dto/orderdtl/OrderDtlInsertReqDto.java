package com.lawencon.orderservice.dto.orderdtl;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrderDtlInsertReqDto {

    @NotBlank
    private String product;
    @NotNull
    private Integer quantity;
    
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
    
}
