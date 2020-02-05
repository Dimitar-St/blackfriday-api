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
		private EntityManager entityManager;

		@Inject
		public UserService(IDatabase database, IPasswordEncryptionAndDecryptionGenerater securePasswordGenerater) {
			this.securePasswordGenerater = securePasswordGenerater;
			this.entityManager = database.createEntityManager();
		}
		
		public String register(UserModel user) {
			String passEncryption = this.securePasswordGenerater.encrypt(user.getPassword(), secret);
			
			user.setPassword(passEncryption);
			
			entityManager.getTransaction().begin();
			
			entityManager.persist(user);
			
			
			entityManager.getTransaction().commit();
			
			String message = "You registerd successfully!";//: "Oops! Something went wrong!";
			
			return message;
		}
		
		public UserModel login(UserModel userToLogIn) {
			
			entityManager.getTransaction().begin();
			
			UserModel foundUser = (UserModel) entityManager.createQuery("SELECT u FROM UserModel u WHERE u.username LIKE :username")
						 								   .setParameter("username", userToLogIn.getUsername())
						 								   .getSingleResult();
			
			
			String passedPassword = userToLogIn.getPassword();
			
			String decryptedPassword = this.securePasswordGenerater.decrypt(foundUser.getPassword(), secret);
			
			if(passedPassword.equals(decryptedPassword)) {
				
				try {
					String jsonToken = SecurityToken.generateJwtToken(String.valueOf(foundUser.getId()));
	
					updateUserToken(foundUser.getId(), jsonToken);
				} catch(Exception e) {
					System.out.println("Log in excpetion: " + e.getMessage());
				}
				
				foundUser.setPassword(decryptedPassword);
				
				
				return foundUser;
			} 	
			
			return null;		
		}
		
		public UserModel getUserById(int id) {
			UserModel foundUser = (UserModel) entityManager.createQuery("SELECT u FROM UserModel u WHERE u.id LIKE :id")
						 								   .setParameter("id", id)
						 								   .getSingleResult();
			
			return foundUser;
		}
		
		private void updateUserToken(int id, String token) {
			UserModel user = entityManager.find(UserModel.class, id);
			
			
			user.setToken(token);
			
			entityManager.getTransaction().commit();
			entityManager.close();
		}
}
