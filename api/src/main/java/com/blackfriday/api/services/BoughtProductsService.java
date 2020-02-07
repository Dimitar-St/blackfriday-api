package com.blackfriday.api.services;

import java.time.LocalDate;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.blackfriday.api.data.models.BoughtProducts;
import com.blackfriday.api.data.models.ProductModel;
import com.blackfriday.api.data.models.UserModel;

import database.IDatabase;
import services.IBoughtProductsService;
import services.IProductService;
import services.IUserService;

public class BoughtProductsService implements IBoughtProductsService {

	private EntityManager entitityManager;
	private IProductService productService;
	private IUserService userService;

	@Inject
	public BoughtProductsService(IDatabase database, IUserService userService, IProductService productService) {
		this.entitityManager = database.createEntityManager();
		this.userService = userService;
		this.productService = productService;
	}

	@Override
	public boolean order(int userId, int productId) {
		UserModel user = this.userService.getUserById(userId);
		ProductModel product = this.productService.getProduct(productId);
		
		BoughtProducts order = new BoughtProducts();
		
		order.setUser(user);
		order.setProduct(product);
		order.setCreatedOn(new Date());
		
		try {
			this.entitityManager.getTransaction().begin();
		
			this.entitityManager.persist(order);
		
			this.entitityManager.getTransaction().commit();
		} catch(Exception e) {
			System.out.println("Exception while making an order: " + e.getMessage());
			
			return false;
		}
		
		return true;
	}

}
