package com.springboot.pmsrestservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.pmsrestservice.dtos.ProductRequest;
import com.springboot.pmsrestservice.dtos.ProductResponse;
import com.springboot.pmsrestservice.entities.Product;
import com.springboot.pmsrestservice.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
	private final ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	private ProductResponse toResponse(Product product) {

		return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
	}

	private Product toEntity(ProductRequest request) {
		Product product = new Product();
		product.setName(request.name());
		product.setDescription(request.description());
		product.setPrice(request.price());
		return product;
	}

	public List<ProductResponse> findAll() {
		return repository.findAll().stream().map(this::toResponse).toList();
	}

	public ProductResponse findById(Long id) {
		return toResponse(repository.findById(id).orElseThrow());
	}

	public ProductResponse create(ProductRequest request) {
		Product productToAdd = toEntity(request);
		Product saved = repository.save(productToAdd);
		return toResponse(saved);
	}

	public ProductResponse update(Long id, ProductRequest request) {

		Product product = repository.findById(id).orElseThrow();

		product.setName(request.name());
		product.setDescription(request.description());
		product.setPrice(request.price());

		return toResponse(repository.save(product));
	}
	
	public ProductResponse delete(Long id) {
		Product product = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return toResponse(product);
    }
}
