package com.blackfriday.api.application;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.blackfriday.api.DAOs.ProductDAO;
import com.blackfriday.api.DAOs.UserDAO;
import com.blackfriday.api.database.Database;
import com.blackfriday.api.password.utils.PasswordEncryptionAndDecryptionGenerater;
import com.blackfriday.api.services.ProductService;
import com.blackfriday.api.services.UserService;

import DAOs.IProductDAO;
import DAOs.IUserDAO;
import database.IDatabase;
import passwordgenerater.IPasswordEncryptionAndDecryptionGenerater;
import services.IProductService;
import services.IUserService;

public class BlackfridayApplicationBuilder extends AbstractBinder {

	@Override
	protected void configure() {
		//Services
		bind(UserService.class).to(IUserService.class);
		bind(ProductService.class).to(IProductService.class);
		
		//Database
		bind(Database.class).to(IDatabase.class);
		
		//Utils
		bind(PasswordEncryptionAndDecryptionGenerater.class).to(IPasswordEncryptionAndDecryptionGenerater.class);
		
		// DAOs
		bind(UserDAO.class).to(IUserDAO.class);
		bind(ProductDAO.class).to(IProductDAO.class);
	}

}
