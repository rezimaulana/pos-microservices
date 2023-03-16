package com.lawencon.orderservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.core.model.BaseEntity;

@Entity
@Table(name = "order_dtl")
public class OrderDtl extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "order_hdr_id")
	private OrderHdr orderHdr;
	
	@Column(name = "product_id", nullable = false)
	private String product;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Column(name = "sub_total", nullable = false)
	private BigDecimal subTotal;

	public OrderHdr getOrderHdr() {
		return orderHdr;
	}

	public void setOrderHdr(OrderHdr orderHdr) {
		this.orderHdr = orderHdr;
	}

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

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	
}
