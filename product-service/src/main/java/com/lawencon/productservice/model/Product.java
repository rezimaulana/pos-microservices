package com.lawencon.productservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.core.model.BaseEntity;

@Entity
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(name = "products_bk", columnNames = { "name" })
})
public class Product extends BaseEntity {

	private static final long serialVersionUID = -5456073211658814994L;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Column(name = "price", nullable = false)
	private BigDecimal price;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
