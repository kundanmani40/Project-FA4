package com.pro.product.service;

import java.util.List;

import com.pro.product.dto.ProductDTO;
import com.pro.product.exception.ProductMsException;

public interface ProductService {
	
	public String addProduct(ProductDTO productDTO) throws ProductMsException;
	
	public String deleteProduct(String id) throws ProductMsException;
	
	public ProductDTO getProductByName(String name) throws ProductMsException;
	
	public List<ProductDTO> getProductByCategory(String category) throws ProductMsException;
	
	public ProductDTO getProductById(String id) throws ProductMsException;

	public Boolean updateStockOfProd(String prodId, Integer quantity) throws ProductMsException;
	
	public List<ProductDTO> viewAllProducts() throws ProductMsException;

}
