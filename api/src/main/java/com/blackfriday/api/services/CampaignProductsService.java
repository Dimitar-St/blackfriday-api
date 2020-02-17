package com.blackfriday.api.services;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.blackfriday.api.data.models.CampaignModel;
import com.blackfriday.api.data.models.CampaignsWithProducts;
import com.blackfriday.api.data.models.ProductModel;

import database.IDatabase;
import services.ICampaignProductsService;
import services.ICampaignService;
import services.IProductService;

public class CampaignProductsService implements ICampaignProductsService {

	private EntityManager entityManager;
	private ICampaignService campaignService;
	private IProductService productService;

	@Inject
	public CampaignProductsService(IDatabase database, ICampaignService campaignService, IProductService productService) {
		this.entityManager = database.createEntityManager();
		this.campaignService = campaignService;
		this.productService = productService;
	}
	
	@Override
	public void addCampaignToAProducts(int campaignId, int productId, double discountPercentage) {
		entityManager.getTransaction().begin();
		
		CampaignsWithProducts data = new CampaignsWithProducts();
		CampaignModel campaign = this.campaignService.getCampaign(campaignId);
		ProductModel product = this.productService.getProduct(productId);
		
		data.setDiscountPercentage(discountPercentage);
		data.setCampaign(campaign);
		data.setProduct(product);
		
		entityManager.persist(data);
		
		entityManager.getTransaction().commit();
	}

}
