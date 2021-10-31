package com.pro.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.pro.product.entity.SubscribedProduct;
import com.pro.product.utility.CompositePK;

public interface SubscribedProductRepository extends CrudRepository<SubscribedProduct, CompositePK> {
	
	

}
