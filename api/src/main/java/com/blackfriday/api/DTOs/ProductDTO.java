package com.blackfriday.api.DTOs;

import com.blackfriday.api.data.models.ProductModel;

public class ProductDTO {
	
	private int id;
	private String name;
	private String manufacturer;
	private String image;
	private String description;
	private int minPrice;
	private int regularPrice;
	private int promotion;
	private int quantity;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(int regularPrice) {
		this.regularPrice = regularPrice;
	}

	public int getPromotion() {
		return promotion;
	}

	public void setPromotion(int promotion) {
		this.promotion = promotion;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public static ProductDTO processDataModel(ProductModel product) {
		ProductDTO productDto = new ProductDTO();
		
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setManufacturer(product.getManufacturer());
		productDto.setRegularPrice(product.getRegularPrice());
		productDto.setDescription(product.getDescription());
		productDto.setQuantity(product.getQuantity());
		productDto.setPromotion(product.getPromotion());
		
		return productDto;
	}
}
