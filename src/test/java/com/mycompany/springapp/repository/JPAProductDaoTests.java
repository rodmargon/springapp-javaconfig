package com.mycompany.springapp.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mycompany.springapp.domain.Product;

public class JPAProductDaoTests {
	
	private ApplicationContext context;
	private ProductDao productDao;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
		productDao = (ProductDao) context.getBean("productDao");
	}

	@Test
	public void testGetProductList() {
		List<Product> productList = productDao.getProductList();
		assertEquals(3,productList.size(),0);
	}

	@Test
	public void testSaveProduct() {
		List<Product> productList = productDao.getProductList();
		
		Product product = productList.get(0);
		Double price = product.getPrice();
		product.setPrice(200.13);
		productDao.saveProduct(product);
		
		List<Product> updatedProductList = productDao.getProductList();
		Product product2 = updatedProductList.get(0);
		assertEquals(200.13,product2.getPrice(),0);
		
		product2.setPrice(price);
		productDao.saveProduct(product2);
		
	}

}
