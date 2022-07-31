package com.revature.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ecommerce.dto.RatingMessage;
import com.revature.ecommerce.entity.Product;
import com.revature.ecommerce.entity.ReviewRating;
import com.revature.ecommerce.exceptions.NoProductException;
import com.revature.ecommerce.service.ProductsSearchService;

@RestController
public class ProductSearchController {

	@Autowired
	private ProductsSearchService service;
	
	@CrossOrigin
	@GetMapping("/search/{searchStr}")
	public List<Product> dispalysarchFormPage(@PathVariable("searchStr") String searchStr) throws NoProductException {
		return service.getProducts(searchStr);
	}
	
	@CrossOrigin
	@GetMapping("/OAR/{prodId}")
	public RatingMessage dispalyOverAllRatingFormPage(@PathVariable("prodId") int prodId ){
		double rating =  service.getOverAllRating( prodId);
		RatingMessage msg =  new RatingMessage(rating);
		return msg;
	}
	
	
}
