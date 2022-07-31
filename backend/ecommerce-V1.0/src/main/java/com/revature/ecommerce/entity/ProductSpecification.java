package com.revature.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="lpu_product_spec")
public class ProductSpecification {
	
	@Id
	@Column(name="spec_id", length=50)
	private String specId;

	@Column(name="spec_name", length=50)
	private String specName;

	@Column(name="spec_value", length=50)
	private String specValue;

	@ManyToOne
	@JoinColumn(name="product_id", referencedColumnName = "product_id")
	private Product product= new Product();

	public ProductSpecification() {
		super();
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getSpecValue() {
		return specValue;
	}

	public void setSpecValue(String specValue) {
		this.specValue = specValue;
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
