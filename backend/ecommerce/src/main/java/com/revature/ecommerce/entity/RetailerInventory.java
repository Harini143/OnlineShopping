package com.revature.ecommerce.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="lpu_retailer")
public class RetailerInventory {
	
	@Id
	@Column(name="retailer_id")
	private int retailerId;
	
	@Column(name="retailer_name")
	@DateTimeFormat
	private String retailerName;
	
	@Column(name="retailer_date")
	LocalDate retailerDate;

	public RetailerInventory() {
	}

	public int getRetailerId() {
		return retailerId;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public LocalDate getRetailerDate() {
		return retailerDate;
	}

	public void setRetailerId(int retailerId) {
		this.retailerId = retailerId;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public void setRetailerDate(LocalDate retailerDate) {
		this.retailerDate = retailerDate;
	}

	@Override
	public String toString() {
		return "RetailerInventory{" +
				"retailerId=" + retailerId +
				", retailerName='" + retailerName + '\'' +
				", retailerDate=" + retailerDate +
				'}';
	}
}
