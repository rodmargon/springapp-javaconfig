package com.mycompany.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.springapp.domain.Product;
import com.mycompany.springapp.repository.ProductDao;

@Component
public class SimpleProductManager implements ProductManager {

	private static final long serialVersionUID = 1L;

	@Autowired
	ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
	
	public void increasePrice(int percentage) {
		List<Product> products = productDao.getProductList();
		if ( products != null ) {
			for (Product product : products) {
				double newprice = product.getPrice().doubleValue() * (percentage+100) / 100;
				product.setPrice(newprice);
				productDao.saveProduct(product);
			}
		}
		

	}

	public List<Product> getProducts() {
		return productDao.getProductList();
	}	

}
