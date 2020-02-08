package com.blackfriday.api.DTOs;

import com.blackfriday.api.data.models.ProductModel;
import com.blackfriday.api.data.models.UserModel;

public class UserDTO {
	
	private String username;
	private String password;
	private String email;
	private String image;
	private String role;
	private int card;
	
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

	public int getCard() {
		return card;
	}

	public void setCard(int card) {
		this.card = card;
	}
	
	public static UserDTO processDataModel(UserModel user) {
		UserDTO userDto = new UserDTO();
		
		userDto.setEmail(user.getEmail());
		userDto.setImage(user.getImage());
		userDto.setCard(user.getCard());
		userDto.setRole(user.getRole());
		userDto.setUsername(user.getUsername());
		
		return userDto;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
