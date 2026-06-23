package com.springboot.pmsrestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.pmsrestservice.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
