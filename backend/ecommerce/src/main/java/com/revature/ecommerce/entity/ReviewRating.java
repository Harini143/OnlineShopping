package com.revature.ecommerce.entity;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "lpu_review_rating")
public class ReviewRating {
    @Id
    @Column(name = "product_rating_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int prodRatingId;

    @Column(name = "product_rating")
    private int rating;

    @Column(name = "product_review")
    private String review;

    @Column(name = "product_review_Dt")
    private String reviewDt;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product = new Product();

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private UserEntity user = new UserEntity();

    public int getProdRatingId() {
        return prodRatingId;
    }

    public void setProdRatingId(int prodRatingId) {
        this.prodRatingId = prodRatingId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getReviewDt() {
        return reviewDt;
    }

    public void setReviewDt(String reviewDt) {
        this.reviewDt = reviewDt;
    }

}
