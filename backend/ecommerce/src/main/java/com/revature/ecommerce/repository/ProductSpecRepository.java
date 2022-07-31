package com.revature.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.ecommerce.entity.ProductSpecification;

public interface ProductSpecRepository extends JpaRepository<ProductSpecification, String> {
}
