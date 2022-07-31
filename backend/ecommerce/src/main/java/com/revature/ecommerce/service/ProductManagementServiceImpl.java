package com.revature.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ecommerce.entity.Product;
import com.revature.ecommerce.exception.ItemExistsException;
import com.revature.ecommerce.repository.ProductJpaRepository;
import com.revature.ecommerce.utils.AppConstants;

import java.util.List;

@Service
public class ProductManagementServiceImpl implements ProductManagementService {

    @Autowired
    ProductJpaRepository productJpaRepository;

    @Override
    public Product saveProduct(Product product) throws ItemExistsException {
        if (productJpaRepository.findById(product.getProductId()).isPresent())
            throw new ItemExistsException(AppConstants.ID_ALREADY_EXISTS);
        return productJpaRepository.save(product);
    }

    @Override
    public Product getProductById(int productId) throws ItemExistsException {
        return productJpaRepository.findById(productId)
                .orElseThrow(() -> new ItemExistsException(AppConstants.ID_NOT_EXISTS));
    }

    @Override
    public Product updateProduct(Integer productId, Product product) throws ItemExistsException {
        Product returnProduct = getProductById(productId);
        returnProduct.setProductName(product.getProductName());
        returnProduct.setBrandName(product.getBrandName());
        returnProduct.setPrice(product.getPrice());
        returnProduct.setProductInfo(product.getProductInfo());
        returnProduct.setQuantity(product.getQuantity());
        return productJpaRepository.save(returnProduct);
    }

    @Override
    public void deleteProduct(Integer productId) throws ItemExistsException {
        if (!productJpaRepository.findById(productId).isPresent())
            throw new ItemExistsException(AppConstants.ID_NOT_EXISTS);
        productJpaRepository.deleteById(productId);
    }

    @Override
    public List<Product> getProducts() {
        return productJpaRepository.findAll();
    }

    @Override
    public List<Product> getRetailerProducts(int id) {
        return productJpaRepository.getProductsOfRetailer(id);
    }
}
