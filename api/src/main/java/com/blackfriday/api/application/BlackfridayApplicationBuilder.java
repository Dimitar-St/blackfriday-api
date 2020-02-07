package com.blackfriday.api.application;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.blackfriday.api.database.Database;
import com.blackfriday.api.services.BoughtProductsService;
import com.blackfriday.api.services.PasswordEncryptionAndDecryptionGenerater;
import com.blackfriday.api.services.ProductService;
import com.blackfriday.api.services.SecurityToken;
import com.blackfriday.api.services.UserService;

import database.IDatabase;
import passwordgenerater.IPasswordEncryptionAndDecryptionGenerater;
import services.IBoughtProductsService;
import services.IProductService;
import services.IUserService;

public class BlackfridayApplicationBuilder extends AbstractBinder {

	@Override
	protected void configure() {
		//Services
		bind(UserService.class).to(IUserService.class);
		bind(ProductService.class).to(IProductService.class);
		bind(BoughtProductsService.class).to(IBoughtProductsService.class);
		
		//Database
		bind(Database.class).to(IDatabase.class);
		
		//Utils
		bind(PasswordEncryptionAndDecryptionGenerater.class).to(IPasswordEncryptionAndDecryptionGenerater.class);
		
		
	}

}
