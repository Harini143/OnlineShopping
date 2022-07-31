package com.revature.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.revature.ecommerce.dto.EcomerceMessage;
import com.revature.ecommerce.entity.Category;
import com.revature.ecommerce.entity.Product;
import com.revature.ecommerce.entity.ProductSpecification;
import com.revature.ecommerce.entity.ReviewRating;
import com.revature.ecommerce.exceptions.NoProductException;
import com.revature.ecommerce.exceptions.NoReviewException;
import com.revature.ecommerce.service.ProductViewService;
import com.revature.ecommerce.util.EcommerceConstants;

@RestController
public class ProductViewController {
	
	@Autowired
	private ProductViewService service;
	
	@CrossOrigin
	@GetMapping("/viewcat")
	public List<Category> getCategories(){
		return service.viewCategories();
	}

	@CrossOrigin
	@GetMapping("/viewprodbyid/{pid}")
	public Product getCategories(@PathVariable("pid") int pid) throws NoProductException {
		return service.viewProductById(pid);
	}
	
	@CrossOrigin
	@GetMapping("/viewprod/{catid}")
	public List<Product> getProducts(@PathVariable("catid") int cid) throws NoProductException{
		return service.viewProducts(cid);
	}
	
	@CrossOrigin
	@GetMapping("/viewprodspec/{prodid}")
	public List<ProductSpecification> getProductSpecifications(@PathVariable("prodid") int pid) throws NoProductException{
		return service.viewProductSpecifications(pid);
	}
	
	@CrossOrigin
	@GetMapping("/viewprodreview/{prodid}")
	public List<ReviewRating> getProductReviewRatings(@PathVariable("prodid") int pid) throws NoReviewException{
		return service.viewProductReviews(pid);
	}

	@CrossOrigin
	@PostMapping("/addReview")
	public EcomerceMessage addReview(@RequestBody ReviewRating review) {
		service.addReviewRating(review);
		return new EcomerceMessage(EcommerceConstants.REVIEW_ADDED);
	}

	
}
