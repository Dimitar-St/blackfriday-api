package com.blackfriday.api.data.models;

import java.util.List;

public class UserModel {
	private String username;
	private String email;
	private String password;
	private String image;
	private String role;
	private List<ProductModel> orders;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<ProductModel> getOrders() {
		return orders;
	}

	public void setOrders(List<ProductModel> orders) {
		this.orders = orders;
	}
}
