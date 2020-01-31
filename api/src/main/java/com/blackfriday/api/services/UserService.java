package com.blackfriday.api.services;

import javax.inject.Inject;

import com.blackfriday.api.DAOs.UserDAO;
import com.blackfriday.api.data.models.UserModel;

import DAOs.IUserDAO;
import passwordgenerater.IPasswordEncryptionAndDecryptionGenerater;
import services.IUserService;

public class UserService implements IUserService {
		private IUserDAO userDao;
		private IPasswordEncryptionAndDecryptionGenerater securePasswordGenerater;

		@Inject
		public UserService(IUserDAO userDao, IPasswordEncryptionAndDecryptionGenerater securePasswordGenerater) {
			this.userDao = userDao;
			this.securePasswordGenerater = securePasswordGenerater;
		}
		
		public String register(UserModel user) {
			//TODO: Password encryption and decryption
			String passEncryption = this.securePasswordGenerater.encrypt(user.getPassword(), "123456");
			
			user.setPassword(passEncryption);
			
			boolean successfullyCreated = this.userDao.create(user);
			String message = successfullyCreated ? "You registerd successfully!" : "Oops! Something went wrong!";
			
			return message;
		}
		
		public UserModel login(UserModel userToLogIn) {
			UserModel foundUser = this.userDao.getUserByUsername(userToLogIn.getUsername());
			
			String passedPassword = userToLogIn.getPassword();
			
			String decryptedPassword = this.securePasswordGenerater.decrypt(foundUser.getPassword(), "123456");
			
			
			if(passedPassword.equals(decryptedPassword)) {
				return foundUser;
			} 	
			
			return null;		
		}
		
		
}
