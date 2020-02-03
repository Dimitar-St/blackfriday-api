package com.blackfriday.api.database;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import database.IDatabase;


public class Database implements IDatabase {

	private EntityManagerFactory entityManagerFactory;


	public Database() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "api" );
	}
	
	
	
	public EntityManager createEntityManager() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		return entityManager;
	}
	

//	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//	private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/black_friday";
//	private Connection db_connection;
//	
//	public Database() {
//		try {
//			Class.forName(JDBC_DRIVER);
//			
//			this.db_connection = DriverManager.getConnection(CONNECTION_STRING, "root", "mypass");
//			
//		} catch (Exception ex) {
//			//TODO: Better exception handling with better message
//			
//			System.out.println(ex);
//		}
//	}
//	
//	public Connection getConnection() {
//		return this.db_connection;
//	}
//	
//	@Override
//	public void close() {
//		try {
//			this.getConnection().close();
//		} catch(Exception ex) {
//			System.out.println(ex);
//		}
//	}

}
