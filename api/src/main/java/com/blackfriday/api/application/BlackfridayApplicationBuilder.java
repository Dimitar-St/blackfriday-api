package com.blackfriday.api.application;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.blackfriday.api.database.Database;
import com.blackfriday.api.services.BoughtProductsService;
import com.blackfriday.api.services.CampaignService;
import com.blackfriday.api.services.SecurityService;
import com.blackfriday.api.services.ProductService;
import com.blackfriday.api.services.SecurityToken;
import com.blackfriday.api.services.UserService;

import database.IDatabase;
import services.IBoughtProductsService;
import services.ICampaignService;
import services.ISecurityService;
import services.IProductService;
import services.IUserService;

public class BlackfridayApplicationBuilder extends AbstractBinder {

	@Override
	protected void configure() {
		//Services
		bind(UserService.class).to(IUserService.class);
		bind(ProductService.class).to(IProductService.class);
		bind(CampaignService.class).to(ICampaignService.class);
		bind(BoughtProductsService.class).to(IBoughtProductsService.class);
		bind(SecurityService.class).to(ISecurityService.class);
		
		
		//Database
		bind(Database.class).to(IDatabase.class);
		
		
	}

}
