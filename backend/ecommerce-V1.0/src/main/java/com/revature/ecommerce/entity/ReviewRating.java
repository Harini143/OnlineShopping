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
    private Long prodRatingId;

    @Column(name = "product_rating", nullable = false)
    private int rating;

    @Column(name = "product_review", nullable = false)
    private String review;

    @Column(name = "product_review_Dt", nullable = false)
    private String reviewDt;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product = new Product();

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email", nullable = false)
    private UserEntity user = new UserEntity();

    public Long getProdRatingId() {
        return prodRatingId;
    }

    public void setProdRatingId(Long prodRatingId) {
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

    @Override
    public String toString() {
        return "ReviewRating{" +
                "prodRatingId=" + prodRatingId +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                ", reviewDt='" + reviewDt + '\'' +
                ", product=" + product +
                ", user=" + user +
                '}';
    }
}
