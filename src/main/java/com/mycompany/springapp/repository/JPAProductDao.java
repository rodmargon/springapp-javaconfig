package com.mycompany.springapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.springapp.domain.Product;

@Repository(value = "productDao")
@Transactional
public class JPAProductDao implements ProductDao {

	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Product> getProductList() {
		// TODO Auto-generated method stub
		return (List<Product>)em.createQuery("select p from Product p order by p.id").getResultList();
	}

	@Transactional(readOnly = false)
	public void saveProduct(Product product) {
		em.merge(product);
	}
}
