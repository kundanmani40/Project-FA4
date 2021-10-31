package com.pro.product.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pro.product.utility.CompositePK;

@Entity
@Table(name = "subscribed_product")
public class SubscribedProduct {
	
	@EmbeddedId
	private CompositePK compositepk;
	public CompositePK getCompositepk() {
		return compositepk;
	}

	public void setCompositepk(CompositePK compositepk) {
		this.compositepk = compositepk;
	}

	private Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}	

}
