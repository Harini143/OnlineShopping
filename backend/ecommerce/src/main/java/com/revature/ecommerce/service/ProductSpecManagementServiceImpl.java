package com.revature.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ecommerce.entity.ProductSpecification;
import com.revature.ecommerce.repository.ProductSpecRepository;

import java.util.Collections;
import java.util.List;

@Service
public class ProductSpecManagementServiceImpl implements ProductSpecManagementService {

    @Autowired
    ProductSpecRepository productSpecRepository;

    @Override
    public List<ProductSpecification> addProductSpec(List<ProductSpecification> specification) {
        return productSpecRepository.saveAll(specification);
    }
}
