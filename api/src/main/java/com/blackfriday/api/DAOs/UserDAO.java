package com.blackfriday.api.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.inject.Inject;

import com.blackfriday.api.data.models.UserModel;
import com.blackfriday.api.database.Database;

import DAOs.IUserDAO;
import database.IDatabase;

public class UserDAO implements IUserDAO<UserModel>  {
	private Connection database;
	
	@Inject
	public UserDAO(IDatabase db) {
		this.database = db.getConnection();
	}

	@Override
	public List<UserModel> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(UserModel user) {
		// TODO Auto-generated method stub
		String username = "('" + user.getUsername() + "', ";
		String password = "'" + user.getPassword() + "', ";
		String email = "'" + user.getEmail() + "', ";
		String image = "'" + user.getImage() + "', ";
		String role = "'" + user.getRole() + "');";
		boolean success = false;
		
		String createUserQuery = QueryEnums.INSERT_INTO.toString() + " " + QueryEnums.TABLE_USER.toString()
								 + "(username, password, email, image, role) VALUES " 
								 + username + password + email + image + role;
		
		System.out.println(createUserQuery);
		
		try {
			Statement statement = this.database.createStatement();
			
			statement.executeUpdate(createUserQuery);
			
			success = true;
		} catch(Exception ex) {
			System.out.println(ex);
		}
		
		return success;
	}

	@Override
	public UserModel processObject(ResultSet rs) {
		// TODO Auto-generated method stub
		UserModel user = new UserModel();
		
		try {
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setImage(rs.getString("image"));
			user.setRole(rs.getString("role"));
			user.setPassword(rs.getString("password"));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		return user;
	}

	@Override
	public void updateImage(String newImage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserModel getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserModel getUserByUsername(String username) {
		// TODO Auto-generated method stub
		String queryUserSelecter = QueryEnums.SELECT.toString() + " id, username, email, role, image, password FROM user WHERE username = " +  "'" + username + "'";
		UserModel foundUser = null;
		
		try {
			Statement stmnt = this.database.createStatement();
			ResultSet result = stmnt.executeQuery(queryUserSelecter);
			
			if(result.next()) {
				foundUser = this.processObject(result);
			}
			
			return foundUser;
		} catch(Exception ex) {
			System.out.println(ex);
		}
		
		return foundUser;
	}

	@Override
	public void updateUsername(String newUsername) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePassword(String newPassword) {
		// TODO Auto-generated method stub
		
	}
	
	
}
