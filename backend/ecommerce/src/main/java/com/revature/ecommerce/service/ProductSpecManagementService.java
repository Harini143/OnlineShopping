package com.revature.ecommerce.service;

import java.util.List;

import com.revature.ecommerce.entity.ProductSpecification;

public interface ProductSpecManagementService {
    List<ProductSpecification> addProductSpec(List<ProductSpecification> specification);
}
