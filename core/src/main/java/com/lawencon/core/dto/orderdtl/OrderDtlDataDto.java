package com.lawencon.core.dto.orderdtl;

import java.math.BigDecimal;

public class OrderDtlDataDto {
    
    private String id;
    private String product;
    private String orderHdr;
    private Integer quantity;
    private BigDecimal subTotal;
    private Boolean isActive;
    private Integer ver;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public String getOrderHdr() {
        return orderHdr;
    }
    public void setOrderHdr(String orderHdr) {
        this.orderHdr = orderHdr;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getSubTotal() {
        return subTotal;
    }
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
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
