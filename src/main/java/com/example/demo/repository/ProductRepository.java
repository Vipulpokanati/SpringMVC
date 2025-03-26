package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	

}
