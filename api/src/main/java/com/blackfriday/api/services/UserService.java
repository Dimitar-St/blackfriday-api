package com.blackfriday.api.services;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;

import com.blackfriday.api.DTOs.Token;
import com.blackfriday.api.data.models.UserModel;

import database.IDatabase;
import services.ISecurityService;
import services.IUserService;

public class UserService implements IUserService {
		private ISecurityService securityService;
		private final String secret = "123456";
		private EntityManager entityManager;
		
		private static final String USER_ALREADY_EXISTS = "User with that username alredy exists";
		private static final String REGISTERED_SUCCESSFULLY = "You registerd successfully!";
		private static final String WRONG_PASSWORD = "The entered password is incorrect!";

		@Inject
		public UserService(IDatabase database, ISecurityService securityService) {
			this.securityService = securityService;
			this.entityManager = database.createEntityManager();
		}
		
		public Response register(UserModel user) {
			int foundUsers = checkIfUserExists(user.getUsername());
			
			if(foundUsers > 0) {
				return Response.status(Response.Status.CREATED).entity(USER_ALREADY_EXISTS).build();
			}
			
			
			String passEncryption = this.securityService.encrypt(user.getPassword(), secret);
			
			user.setPassword(passEncryption);
			
			entityManager.getTransaction().begin();
			
			entityManager.persist(user);
			
			
			entityManager.getTransaction().commit();
			
			return Response.ok(REGISTERED_SUCCESSFULLY).build();
		}
		
		public Response login(UserModel userToLogIn) {
			entityManager.getTransaction().begin();
			
			UserModel foundUser = (UserModel) entityManager.createQuery("SELECT u FROM UserModel u WHERE u.username LIKE :username")
						 								   .setParameter("username", userToLogIn.getUsername())
						 								   .getSingleResult();
			
			String passedPassword = userToLogIn.getPassword();
			
			String decryptedPassword = this.securityService.decrypt(foundUser.getPassword(), secret);
			
			String jsonToken = null;
			
			if(passedPassword.equals(decryptedPassword)) {
				
				try {
					jsonToken = SecurityToken.generateJwtToken(String.valueOf(foundUser.getId()));
	
					updateUserToken(foundUser.getId(), jsonToken);
				} catch(Exception e) {
					System.out.println("Log in excpetion: " + e.getMessage());
				}
				
				
				Token token = new Token();
				
				token.setToken(jsonToken);
				
				entityManager.getTransaction().commit();
				
				return Response.ok().entity(token).build();
			} else {
				entityManager.getTransaction().commit();
				
				return Response.status(Response.Status.NOT_FOUND).entity(WRONG_PASSWORD).build();
			}
		}
		
		public UserModel getUserById(int id) {
			
			UserModel foundUser = (UserModel) entityManager.createQuery("SELECT u FROM UserModel u WHERE u.id LIKE :id")
						 								   .setParameter("id", id)
						 								   .getSingleResult();
			
			return foundUser;
		}
		
		public int checkIfUserExists(String username) {
			entityManager.getTransaction().begin();
			
			List<UserModel> foundUsers = entityManager.createQuery("SELECT u FROM UserModel u WHERE u.username LIKE :username", UserModel.class)
					   									   .setParameter("username", username)
					                                       .getResultList();
			
			entityManager.getTransaction().commit();
			
			return foundUsers.size();
		}
		
		private void updateUserToken(int id, String token) {
			
			UserModel user = getUserById(id);
			
			user.setToken(token);
		}
}
