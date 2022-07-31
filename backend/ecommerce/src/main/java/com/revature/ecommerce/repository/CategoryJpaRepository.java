package com.revature.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.ecommerce.entity.Category;

@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, Integer> {
   Category findFirstByCategoryNameIgnoreCase(String categoryName);
}
