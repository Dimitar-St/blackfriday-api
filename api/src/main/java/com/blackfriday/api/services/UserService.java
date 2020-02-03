package com.blackfriday.api.services;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.blackfriday.api.data.models.UserModel;

import database.IDatabase;
import passwordgenerater.IPasswordEncryptionAndDecryptionGenerater;
import services.IUserService;

public class UserService implements IUserService {
		private IPasswordEncryptionAndDecryptionGenerater securePasswordGenerater;
		private final String secret = "123456";
		private IDatabase database;

		@Inject
		public UserService(IDatabase database, IPasswordEncryptionAndDecryptionGenerater securePasswordGenerater) {
			this.database = database;
			this.securePasswordGenerater = securePasswordGenerater;
		}
		
		public String register(UserModel user) {
			String passEncryption = this.securePasswordGenerater.encrypt(user.getPassword(), secret);
			
			user.setPassword(passEncryption);
			
			EntityManager entityManager = this.database.createEntityManager();
			entityManager.getTransaction().begin();
			
			entityManager.persist(user);
			
			
			entityManager.getTransaction().commit();
			entityManager.close();
			
			String message = "You registerd successfully!";//: "Oops! Something went wrong!";
			
			return message;
		}
		
		public UserModel login(UserModel userToLogIn) {
			
			EntityManager entityManager = this.database.createEntityManager();
			entityManager.getTransaction().begin();
			
			UserModel foundUser = (UserModel) entityManager.createQuery("SELECT u FROM UserModel u WHERE u.username LIKE :username")
						 								   .setParameter("username", userToLogIn.getUsername())
						 								   .getSingleResult();
			
			
			String passedPassword = userToLogIn.getPassword();
			
			String decryptedPassword = this.securePasswordGenerater.decrypt(foundUser.getPassword(), secret);
			
			
			if(passedPassword.equals(decryptedPassword)) {
				return foundUser;
			} 	
			
			return null;		
		}
		
		
}
