package com.revature.ecommerce.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.ecommerce.dao.EcommerceDao;
import com.revature.ecommerce.entity.Product;
import com.revature.ecommerce.entity.ReviewRating;
import com.revature.ecommerce.exceptions.NoProductException;
import com.revature.ecommerce.util.EcommerceConstants;

@Service
@Transactional(readOnly = true)
public class ProductsSearchServiceImpl implements ProductsSearchService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EcommerceDao dao;

    @Override
    public List<Product> getProducts(String searchStr) throws NoProductException {
        List<Product> plist = dao.getProducts(searchStr);

        if (plist.isEmpty())
            throw new NoProductException(EcommerceConstants.NO_PRODUCTS_AVAILABLE);
        plist.stream().forEach(p1 -> p1.setOverAllRating(getOverAllRating(p1.getProductId())));
        plist.sort((p1, p2) -> p1.getProductName().compareTo(p2.getProductName()));
        return plist;
    }

    @Override
    public Double getOverAllRating(int prodId) {
        List<ReviewRating> plist = dao.getProductReviews(prodId);
        Double overallRating = 0.0;
        if (!plist.isEmpty())
            overallRating = plist.stream().mapToDouble(p -> p.getRating()).average().getAsDouble();
        return overallRating;
    }


}
