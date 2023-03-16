package com.lawencon.orderservice.dto.orderhdr;

import java.math.BigDecimal;
import java.util.List;

import com.lawencon.orderservice.dto.orderdtl.OrderDtlDataDto;

public class OrderHdrDataDto {
    
    private String id;
    private String trxCode;
    private String customerName;
    private String employee;
    private BigDecimal grandTotal;
    private List<OrderDtlDataDto> detail;
    private Boolean isActive;
    private Integer ver;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTrxCode() {
        return trxCode;
    }
    public void setTrxCode(String trxCode) {
        this.trxCode = trxCode;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getEmployee() {
        return employee;
    }
    public void setEmployee(String employee) {
        this.employee = employee;
    }
    public BigDecimal getGrandTotal() {
        return grandTotal;
    }
    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }
    public List<OrderDtlDataDto> getDetail() {
        return detail;
    }
    public void setDetail(List<OrderDtlDataDto> detail) {
        this.detail = detail;
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
