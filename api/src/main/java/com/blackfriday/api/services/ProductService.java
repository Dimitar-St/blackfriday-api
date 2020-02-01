package com.blackfriday.api.services;

import java.util.List;

import javax.inject.Inject;

import com.blackfriday.api.data.models.ProductModel;

import DAOs.IProductDAO;
import services.IProductService;

public class ProductService implements IProductService {
	
	private IProductDAO productDao;

	@Inject
	public ProductService(IProductDAO productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<ProductModel> getAll() {
		List<ProductModel> products = this.productDao.getAll();
		
		System.out.println(products.get(0).getName());
		
		return products;
	}

	@Override
	public String addAProduct(ProductModel product) {
		boolean isSuccessfullyAdded = this.productDao.create(product);
		String message = isSuccessfullyAdded ? "The product was added successfully" : "Ops! Something got wrong!";
		
		
		return message;
	}

	@Override
	public String removeAProduct(ProductModel product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modifyAProduct(ProductModel product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductModel> getProductsBy(String property, String value) {
		List<ProductModel> products = this.productDao.getProductsBy(property, value);
		
		return products;
	}

}
