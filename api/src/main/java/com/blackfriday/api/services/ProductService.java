package com.blackfriday.api.services;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.blackfriday.api.data.models.ProductModel;

import database.IDatabase;
import services.IProductService;

public class ProductService implements IProductService {
	
	private IDatabase database;

	@Inject
	public ProductService(IDatabase database) {
		this.database = database;
	}

	@Override
	public List<ProductModel> getAll() {
		EntityManager entityManager = this.database.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<ProductModel> products = entityManager.createQuery("from ProductModel", ProductModel.class).getResultList();
		
		for(int i = 0; i < products.size(); i++) {
			ProductModel product = products.get(i);
			
			if(product.getIsDeleted() == true) {
				products.remove(i);
			}
		}
		
		entityManager.getTransaction().commit();
		
		return products;
	}
	
	@Override
	public ProductModel getProduct(int id) {
		EntityManager entityManager = this.database.createEntityManager();
		entityManager.getTransaction().begin();
		
		ProductModel product = (ProductModel) entityManager.createQuery("SELECT p FROM ProductModel p WHERE p.id LIKE :id")
														   .setParameter("id", id)
														   .getResultList().get(0);
		
		entityManager.getTransaction().commit();
		
		return product;
	}

	@Override
	public String addAProduct(ProductModel product) {
		EntityManager entityManager = this.database.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(product);
		
		entityManager.getTransaction().commit();
		String message = "The product was added successfully" ;
		
		
		return message;
	}

	@Override
	public String removeAProduct(int id) {
		// TODO Auto-generated method stub
		EntityManager entityManager = this.database.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.createQuery("UPDATE ProductModel p SET p.isDeleted = true WHERE p.id LIKE :id").setParameter("id", id).executeUpdate();
		
		entityManager.getTransaction().commit();
		
		String message = "Succesfully deleted";
		
		return message;
	}

	@Override
	public String modifyAProduct(ProductModel product) {
		// TODO Auto-generated method stub
		return null;
	}

}
