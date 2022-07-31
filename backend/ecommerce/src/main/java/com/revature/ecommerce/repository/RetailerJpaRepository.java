package com.revature.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.ecommerce.entity.RetailerInventory;

import java.util.List;

@Repository
public interface RetailerJpaRepository extends JpaRepository<RetailerInventory, Integer> {
    List<RetailerInventory> findByRetailerNameContainingIgnoreCase(String name);
}
