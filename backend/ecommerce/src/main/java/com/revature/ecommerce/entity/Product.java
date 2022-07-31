package com.revature.ecommerce.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="lpu_product")
public class Product {

	@Id
	@Column(name="product_id")
	private int productId;

	@Column(name="product_name", length=100)
	private String productName;

	@Column(name="product_info")
	private String productInfo;

	@Column(name="price")
	private double price;

	@Column(name="brand_name", length=50)
	private String brandName;

	@Column(name = "quantity")
	private int quantity;

	@Transient
	private Double overAllRating;

	@JsonProperty
	@ManyToOne
	@JoinColumn(name="category_id", referencedColumnName = "category_id")
	private Category category = new Category();

	@JsonProperty
	@ManyToOne
	@JoinColumn(name="retailer_id", referencedColumnName = "retailer_id")
	private RetailerInventory inventory = new RetailerInventory();

	public Product() {
	}

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public String getBrandName() {
		return brandName;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public double getPrice() {
		return price;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setInventory(RetailerInventory inventory) {
		this.inventory = inventory;
	}

	public Category getCategory() {
		return category;
	}

	public RetailerInventory getInventory() {
		return inventory;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Double getOverAllRating() {
		return overAllRating;
	}

	public void setOverAllRating(Double overAllRating) {
		this.overAllRating = overAllRating;
	}

	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", productName='" + productName + '\'' +
				", productInfo='" + productInfo + '\'' +
				", price=" + price +
				", brandName='" + brandName + '\'' +
				", quantity=" + quantity +
				", category=" + category +
				", inventory=" + inventory +
				'}';
	}
}
