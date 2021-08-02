package com.mmit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mmit.entity.Product;
import com.mmit.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/products")
	public List<Product> listAll()
	{
		return service.findAll();
	}
	
	@PostMapping("/product")
	public void addProduct(@RequestBody Product product)
	{
		service.save(product);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProduct(@PathVariable int id)
	{
		try {
			
			Product p=service.findById(id);
			return new ResponseEntity<Product>(p,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);//for response error beautiful
		}
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<?> updateProduct(@RequestBody Product p,@PathVariable int id)
	{
		//"id":1 in raw data because of bind
		try {
			Product product=service.findById(id);
			service.save(p);
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id)
	{
		try {
			Product p=service.findById(id);
			service.deleteProduct(id);
			return new ResponseEntity<Product>(p,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
