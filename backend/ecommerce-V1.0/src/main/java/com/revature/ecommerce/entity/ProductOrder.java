package com.revature.ecommerce.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "lpu_product_order")
public class ProductOrder {

    @Id
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_status")
    private String status;

    @Column(name = "order_date")
    private LocalDate orderDate;


	@ManyToOne
	@JoinColumn(name = "address_id", referencedColumnName = "address_id")
	Address address = new Address();

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "email", referencedColumnName = "email")
	private UserEntity user = new UserEntity();

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
