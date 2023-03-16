package com.lawencon.orderservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.core.model.BaseEntity;

@Entity
@Table(name = "order_hdr", uniqueConstraints = {
        @UniqueConstraint(name = "order_hdr_bk", columnNames = { "trx_code" })
})
public class OrderHdr extends BaseEntity {

	@Column(name = "trx_code", nullable = false)
	private String trxCode;
	
	@Column(name = "customer_name", nullable = false)
	private String customerName;
	
	@Column(name = "grand_total", nullable = false)
	private BigDecimal grandTotal;
	
	@Column(name = "employee_id", nullable = false)
	private String employee;
	
	public String getTrxCode() {
		return trxCode;
	}

	public void setTrxCode(String trxCode) {
		this.trxCode = trxCode;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
