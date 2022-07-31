package com.revature.ecommerce.service;

import java.util.List;

import com.revature.ecommerce.entity.Category;
import com.revature.ecommerce.entity.Product;
import com.revature.ecommerce.exception.ItemExistsException;

public interface ProductManagementService {

    Product saveProduct(Product product) throws ItemExistsException;
    Product getProductById(int productId) throws ItemExistsException;
    Product updateProduct(Integer productId, Product product) throws ItemExistsException;
    void deleteProduct(Integer productId) throws ItemExistsException;
    List<Product> getProducts();
    List<Product> getRetailerProducts(int id);
}
