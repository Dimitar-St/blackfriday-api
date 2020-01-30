package com.blackfriday.api.application;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.blackfriday.api.DAOs.UserDAO;
import com.blackfriday.api.database.Database;
import com.blackfriday.api.services.UserService;

import api.IUserDAO;
import database.IDatabase;
import services.IUserService;

public class BlackfridayApplicationBiulder extends AbstractBinder {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(UserService.class).to(IUserService.class);
		bind(UserDAO.class).to(IUserDAO.class);
		bind(Database.class).to(IDatabase.class);
	}

}
