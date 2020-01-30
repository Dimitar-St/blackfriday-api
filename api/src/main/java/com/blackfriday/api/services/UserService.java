package com.blackfriday.api.services;

import javax.inject.Inject;

import com.blackfriday.api.DAOs.UserDAO;
import com.blackfriday.api.data.models.UserModel;

import api.IUserDAO;
import services.IUserService;

public class UserService implements IUserService {
		private IUserDAO userDao;

		@Inject
		public UserService(IUserDAO userDao) {
			this.userDao = userDao;
		}
		
		public String register(UserModel user) {
			//TODO: Password encryption and decryption
			
			boolean successfullyCreated = this.userDao.create(user);
			String message = successfullyCreated ? "You registerd successfully!" : "Oops! Something went wrong!";
			
			return message;
		}
		
		
}
