package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.validation.validator.ValidCoupon;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@SequenceGenerator(name="customer_id_SEQ", sequenceName="customer_id_SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY ,generator="customer_id_SEQ")
	@Column(name = "customer_id")
	private Integer customerId;

	@NotNull(message = "user name is a required field")
	@Size(min = 1, max = 20, message = "user name cannot be longer than 10 characters")
	@Column(name="username")
	private String username;

	@NotNull(message = "password is a required field")
	@Size(min = 1, max = 20, message = "password cannot be longer than 20 characters")
	@Column(name="password")
	private String password;
	
	@NotNull(message = "coupon is a required field")
	@ValidCoupon
	@Column(name="coupon")
	private String coupon;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	
}
