package com.revature.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="lpu_product_spec")
public class ProductSpecification {

	@Id
	@Column(name="spec_id", length=30)
	private String specId;

	@Column(name="spec_name", length=30)
	private String specName;

	@Column(name="spec_value", length=30)
	private String specValue;

	@JsonProperty
	@ManyToOne
	@JoinColumn(name="product_id", referencedColumnName = "product_id")
	private Product product = new Product();

	public ProductSpecification() {
		super();
	}

	public ProductSpecification(String specId, String specName, String specValue, Product product) {
		this.specId = specId;
		this.specName = specName;
		this.specValue = specValue;
		this.product = product;
	}

	public String getSpecId() {
		return specId;
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

}
