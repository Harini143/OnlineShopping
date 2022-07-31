package com.revature.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.ecommerce.dao.EcommerceDao;
import com.revature.ecommerce.entity.Category;
import com.revature.ecommerce.entity.Product;
import com.revature.ecommerce.entity.ProductSpecification;
import com.revature.ecommerce.entity.ReviewRating;
import com.revature.ecommerce.exceptions.NoProductException;
import com.revature.ecommerce.exceptions.NoReviewException;
import com.revature.ecommerce.util.EcommerceConstants;

@Service
@Transactional
public class ProductViewServiceImpl implements ProductViewService {

    @Autowired
    private EcommerceDao dao;

    @Autowired
    private ProductsSearchService searchService;

    @Override
    public List<Category> viewCategories() {
        List<Category> catList = dao.getCategories();
        catList.sort((c1, c2) -> c1.getCategoryName().compareTo(c2.getCategoryName()));
        return catList;
    }

    @Override
    public List<Product> viewProducts(int catId) throws NoProductException {
        List<Product> plist = dao.getProducts(catId);
        if (plist.isEmpty())
            throw new NoProductException(EcommerceConstants.NO_PRODUCTS_AVAILABLE);
        plist.forEach(p1 -> p1.setOverAllRating(searchService.getOverAllRating(p1.getProductId())));
        plist.sort((p1, p2) -> p2.getOverAllRating().compareTo(p1.getOverAllRating()));
        return plist;
    }

    @Override
    public List<ProductSpecification> viewProductSpecifications(int productId) throws NoProductException {
        List<ProductSpecification> specList = dao.getProductSpecifications(productId);
        if (specList.isEmpty())
            throw new NoProductException(EcommerceConstants.NO_PRODUCTS_SPEC_AVAILABLE);
        specList.sort((spec1, spec2) -> spec1.getSpecName().compareTo(spec2.getSpecName()));
        return specList;
    }

    @Override
    public List<ReviewRating> viewProductReviews(int proId) throws NoReviewException {
        List<ReviewRating> reviewList = dao.getProductReviews(proId);
        if (reviewList.isEmpty())
            throw new NoReviewException(EcommerceConstants.NO_REVIEWS_AVAILABLE);
        reviewList.sort((p1, p2) -> p2.getReviewDt().compareTo(p1.getReviewDt()));
        return reviewList;
    }

    @Override
    public Product viewProductById(Integer pid) throws NoProductException {
        Product product = dao.getProduct(pid);
        if (product == null)
            throw new NoProductException(EcommerceConstants.NO_PRODUCTS_AVAILABLE);
        product.setOverAllRating(searchService.getOverAllRating(product.getProductId()));
        return product;
    }

    @Override
    public boolean addReviewRating(ReviewRating reviewRating) {
        dao.addReviewRating(reviewRating);
        return true;
    }

}
