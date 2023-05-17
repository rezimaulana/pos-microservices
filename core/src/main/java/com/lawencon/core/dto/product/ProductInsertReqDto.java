package com.lawencon.core.dto.product;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductInsertReqDto {
    
    @NotBlank
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer quantity;
    private String encode;
    private String extension;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

}
