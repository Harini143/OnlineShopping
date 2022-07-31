package com.revature.ecommerce.service;

import java.util.List;

import com.revature.ecommerce.entity.Category;
import com.revature.ecommerce.entity.Product;
import com.revature.ecommerce.entity.ProductSpecification;
import com.revature.ecommerce.entity.ReviewRating;
import com.revature.ecommerce.exceptions.NoProductException;
import com.revature.ecommerce.exceptions.NoReviewException;

public interface ProductViewService {
	
	List<Category> viewCategories();
	List<Product> viewProducts(int catId)throws NoProductException;
	List<ProductSpecification> viewProductSpecifications(int productId)throws NoProductException;
	List<ReviewRating> viewProductReviews(int proId)throws NoReviewException;
	Product viewProductById(Integer pid) throws NoProductException;
	boolean addReviewRating(ReviewRating reviewRating);

}
