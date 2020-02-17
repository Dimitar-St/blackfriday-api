package com.blackfriday.api.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="campaigns_products")
public class CampaignsWithProducts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private ProductModel product;
	
	@ManyToOne
	private CampaignModel campaign;
	
	@Column(name="discount_persentage")
	private double discountPercentage;

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

	public CampaignModel getCampaign() {
		return campaign;
	}

	public void setCampaign(CampaignModel campaign) {
		this.campaign = campaign;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
}
