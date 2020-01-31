package com.blackfriday.api.application;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.blackfriday.api.DAOs.UserDAO;
import com.blackfriday.api.database.Database;
import com.blackfriday.api.password.utils.PasswordEncryptionAndDecryptionGenerater;
import com.blackfriday.api.services.UserService;

import DAOs.IUserDAO;
import database.IDatabase;
import passwordgenerater.IPasswordEncryptionAndDecryptionGenerater;
import services.IUserService;

public class BlackfridayApplicationBuilder extends AbstractBinder {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(UserService.class).to(IUserService.class);
		bind(UserDAO.class).to(IUserDAO.class);
		bind(Database.class).to(IDatabase.class);
		bind(PasswordEncryptionAndDecryptionGenerater.class).to(IPasswordEncryptionAndDecryptionGenerater.class);
	}

}
