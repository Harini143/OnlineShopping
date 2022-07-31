package com.revature.ecommerce.service;

import java.util.List;

import com.revature.ecommerce.entity.Product;
import com.revature.ecommerce.entity.ReviewRating;
import com.revature.ecommerce.exceptions.NoProductException;


public interface ProductsSearchService {

	public List<Product> getProducts(String searchStr)throws NoProductException;
	public Double getOverAllRating(int prodId);
}
