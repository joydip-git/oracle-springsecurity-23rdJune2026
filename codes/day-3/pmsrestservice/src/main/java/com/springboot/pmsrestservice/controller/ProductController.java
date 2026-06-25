package com.springboot.pmsrestservice.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pmsrestservice.dtos.ProductRequest;
import com.springboot.pmsrestservice.dtos.ProductResponse;
import com.springboot.pmsrestservice.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public Collection<ProductResponse> getProducts() {
		return productService.findAll();
	}

	@GetMapping("/{id}")
	public ProductResponse getProduct(@PathVariable Long id) {
		return productService.findById(id);
	}

	@PostMapping
	public ProductResponse addProduct(@RequestBody ProductRequest product) {
		return productService.create(product);
	}

	@PutMapping("/{id}")
	public ProductResponse updateProduct(@PathVariable Long id, @RequestBody ProductRequest product) {
		return productService.update(id, product);
	}
	
	@DeleteMapping("/{id}")
	public ProductResponse deleteProduct(@PathVariable Long id) {
		return productService.delete(id);
	}
}
