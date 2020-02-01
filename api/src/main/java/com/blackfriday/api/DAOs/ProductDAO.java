package com.blackfriday.api.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.blackfriday.api.data.models.ProductModel;
import com.blackfriday.api.data.models.UserModel;

import DAOs.IProductDAO;
import database.IDatabase;

public class ProductDAO implements IProductDAO<ProductModel> {

	private Connection database;

	@Inject
	public ProductDAO(IDatabase db) {
		this.database = db.getConnection();
	}

	@Override
	public List<ProductModel> getAll() {
		String query = QueryEnums.SELECT.toString() + " * " + "FROM " + QueryEnums.TABLE_PRODUCT.toString();
		List<ProductModel> products = new ArrayList<ProductModel>();

		try {
			PreparedStatement stmnt = this.database.prepareStatement(query);
			ResultSet result = stmnt.executeQuery();

			while (result.next()) {
				ProductModel product = this.processObject(result);
				products.add(product);
			}
		} catch (Exception e) {
			System.out.println("DAO product exception: " + e.toString());
		}

		return products;
	}

	@Override
	public boolean create(ProductModel obj) {
		boolean success = false;
		
		String name = "('" + obj.getName() + "', ";
		String category = "'" + obj.getCategory() + "', ";
		String image = "'" + obj.getImage() + "', ";
		String price = "'" + obj.getPrice() + "', ";
		String promotion = "'" + obj.getPromotion() + "', ";
		String manufacturer = "'" + obj.getManufacturer() + "', ";
		boolean isDeleted = obj.isDeleted();
		String description = ", '" + obj.getDescription() + "');";

		String addProductQuery = QueryEnums.INSERT_INTO.toString() + " " + QueryEnums.TABLE_PRODUCT.toString()
				+ "(name, category, image, price, promotion, manufacturer, isDeleted, description) VALUES " + name + category + image + price
				+ promotion + manufacturer + isDeleted + description;

		System.out.println(addProductQuery);
		
		
		try {
			Statement statement = this.database.createStatement();
			
			statement.executeUpdate(addProductQuery);
			
			success = true;
		} catch(Exception ex) {
			System.out.println(ex);
		}

		return success;
	}

	@Override
	public ProductModel processObject(ResultSet rs) {
		ProductModel product = new ProductModel();

		try {
			product.setName(rs.getString("name"));
			product.setCategory(rs.getString("category"));
			product.setManufacturer(rs.getString("manufacturer"));
			product.setDeleted(false);
			product.setPrice(rs.getInt("price"));
			product.setPromotion(rs.getInt("promotion"));
			product.setDescription(rs.getString("description"));
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return product;
	}

	@Override
	public void updateImage(String newImage) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ProductModel> getProductsBy(String property, String value) {
		String queryProductSelecter = QueryEnums.SELECT.toString() + " name, category, manufacturer, price, promotion, description FROM product WHERE "+ property + " = " + "'" + value + "'";
		List<ProductModel> foundProducts = new ArrayList<ProductModel>();
		
		try {
			Statement stmnt = this.database.createStatement();
			ResultSet result = stmnt.executeQuery(queryProductSelecter);
			
			while(result.next()) {
				ProductModel product = this.processObject(result);
				foundProducts.add(product);
			}
			
			return foundProducts;
		} catch(Exception ex) {
			System.out.println(ex);
		}
		
		return null;
	}

}
