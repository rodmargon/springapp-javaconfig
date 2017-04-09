package com.mycompany.springapp.repository;

import java.util.List;

import com.mycompany.springapp.domain.Product;

public interface ProductDao {
	
	public List<Product> getProductList();
	
	public void saveProduct(Product product);
	
}
