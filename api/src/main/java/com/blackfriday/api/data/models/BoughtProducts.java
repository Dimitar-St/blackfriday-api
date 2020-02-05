package com.blackfriday.api.data.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bought_products")
public class BoughtProducts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@ManyToOne
	private ProductModel product;
	
	@ManyToOne
	private UserModel user;
	
	@Column(name = "created_on")
    private Date createdOn;
	
	public BoughtProducts() {}
	
	public BoughtProducts(UserModel user, ProductModel product) {
		this.product = product;
		this.user = user;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public ProductModel getProduct() {
		return product;
	}


	public void setProduct(ProductModel product) {
		this.product = product;
	}


	public UserModel getUser() {
		return user;
	}


	public void setUser(UserModel user) {
		this.user = user;
	}


	public Date getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
}
