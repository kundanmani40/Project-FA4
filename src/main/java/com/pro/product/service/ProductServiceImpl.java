package com.pro.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pro.product.dto.ProductDTO;
import com.pro.product.entity.Product;
import com.pro.product.exception.ProductMsException;
import com.pro.product.repository.ProductRepository;
import com.pro.product.validator.ProductValidator;

@Service(value = "productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	//Creating unique Pid
	private static int pId;
	
	static {
		pId=1000;
	}
	//Method for adding product
	@Override
	public String addProduct(ProductDTO productDTO) throws ProductMsException {
		
		ProductValidator.validateProduct(productDTO);
		
		Product product = productRepository.findByProductName(productDTO.getProductName());
		
		if(product != null)
			throw new ProductMsException("Service.PRODUCT_ALREADY_EXISTS");
		
		product = new Product();
		
		String id = "P"+pId++;
		
		product.setProdId(id);
		product.setProductName(productDTO.getProductName());
		product.setPrice(productDTO.getPrice());
		product.setCategory(productDTO.getCategory());
		product.setDescription(productDTO.getDescription());
		product.setImage(productDTO.getImage());
		product.setSubCategory(productDTO.getSubCategory());
		product.setSellerId(productDTO.getSellerId());
		product.setProductRating(productDTO.getProductRating());
		product.setStock(productDTO.getStock());
		
		productRepository.save(product);
		
		return product.getProdId();
	}
	
	//method for getting all product
	@Override
	public List<ProductDTO> viewAllProducts() throws ProductMsException {
		
		List<Product> list = productRepository.findAll();
		
		if(list.isEmpty())
			throw new ProductMsException("There are no products");
		
		List<ProductDTO> list1 = new ArrayList<>();
		
		list.forEach(i -> {
			ProductDTO prod = new ProductDTO();
			prod.setCategory(i.getCategory());
			prod.setDescription(i.getDescription());
			prod.setImage(i.getImage());
			prod.setPrice(i.getPrice());
			prod.setProdId(i.getProdId());
			prod.setProductName(i.getProductName());
			prod.setProductRating(i.getProductRating());
			prod.setSellerId(i.getSellerId());
			prod.setStock(i.getStock());
			prod.setSubCategory(i.getSubCategory());
			
			list1.add(prod);
		});
		
		return list1;
	}

 //method for getting product by name
	@Override
	public ProductDTO getProductByName(String name) throws ProductMsException {
		
		Product product = productRepository.findByProductName(name);
		
		if(product == null)
			throw new ProductMsException("Service.PRODUCT_NOT_FOUND");
		
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setProdId(product.getProdId());
		productDTO.setCategory(product.getCategory());
		productDTO.setDescription(product.getDescription());
		productDTO.setImage(product.getImage());
		productDTO.setPrice(product.getPrice());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductRating(product.getProductRating());
		productDTO.setSellerId(product.getSellerId());
		productDTO.setStock(product.getStock());
		productDTO.setSubCategory(product.getCategory());
		
		return productDTO;
	}
	
	//method for getting product by Id
	@Override
	public ProductDTO getProductById(String id) throws ProductMsException {
		
		Product product = productRepository.findByProdId(id);
		
		if(product == null)
			throw new ProductMsException("Service.PRODUCT_NOT_FOUND");
		
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setProdId(product.getProdId());
		productDTO.setCategory(product.getCategory());
		productDTO.setDescription(product.getDescription());
		productDTO.setImage(product.getImage());
		productDTO.setPrice(product.getPrice());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductRating(product.getProductRating());
		productDTO.setSellerId(product.getSellerId());
		productDTO.setStock(product.getStock());
		productDTO.setSubCategory(product.getCategory());
		
		return productDTO;
	}
 //method for get product by Category
	@Override
	public List<ProductDTO> getProductByCategory(String category) throws ProductMsException {
		
		List<Product> list = productRepository.findByCategory(category);
		
		if(list.isEmpty())
			throw new ProductMsException("Service.CATEGORY_ERROR");
		
		List<ProductDTO> list1 = new ArrayList<>();
		
		for(Product product : list)
		{
			ProductDTO productDTO = new ProductDTO();
			
			productDTO.setProdId(product.getProdId());
			productDTO.setCategory(product.getCategory());
			productDTO.setDescription(product.getDescription());
			productDTO.setImage(product.getImage());
			productDTO.setPrice(product.getPrice());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductRating(product.getProductRating());
			productDTO.setSellerId(product.getSellerId());
			productDTO.setStock(product.getStock());
			productDTO.setSubCategory(product.getCategory());
			
			list1.add(productDTO);
		}
		
		return list1;
	}
//method for removing product
	@Override
	public String deleteProduct(String id) throws ProductMsException {
		
		Product product = productRepository.findByProdId(id);
		
		if(product == null)
			throw new ProductMsException("Service.PRODUCT_NOT_AVAILABLE");
		
		productRepository.delete(product);
		
		return "Product successfully deleted";
		
	}

//method for updating product	
	@Override
	public Boolean updateStockOfProd(String prodId, Integer quantity) throws ProductMsException {
		Optional<Product> optional = productRepository.findById(prodId);
		Product product = optional.orElseThrow(()-> new ProductMsException("Product does not exist"));
		if(product.getStock()>=quantity) {
			product.setStock(product.getStock()-quantity);
			return true;
		}
		return false;		
	}

	

}
