package com.revature.ecommerce.service;

import java.util.List;

import com.revature.ecommerce.entity.Category;
import com.revature.ecommerce.exception.ItemExistsException;

public interface CategoryManagementService {
    Category saveCategory(Category category) throws ItemExistsException;
    Category getCategoryById(int categoryId) throws ItemExistsException;
    Category updateCategory(Integer categoryId, Category category) throws ItemExistsException;
    void deleteCategory(Integer categoryId) throws ItemExistsException;
    List<Category> getCategories();
}
