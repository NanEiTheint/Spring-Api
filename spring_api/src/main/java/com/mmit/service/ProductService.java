package com.mmit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.entity.Product;
import com.mmit.entity.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo repo;

	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	public void save(Product product) {
		repo.save(product);
		
	}
	public Product findById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}
	public void deleteProduct(int id) {
		repo.deleteById(id);
		
	}

}
