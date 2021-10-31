package com.pro.product.utility;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class CompositePK implements Serializable {
	
	protected String buyerId;
	protected String prodId;
	
	public CompositePK() {
		super();
	}

	public CompositePK(String buyerId, String prodId) {
		super();
		this.buyerId = buyerId;
		this.prodId = prodId;
	}
	
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(buyerId, prodId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositePK other = (CompositePK) obj;
		return Objects.equals(buyerId, other.buyerId) && Objects.equals(prodId, other.prodId);
	}
	
	

}
