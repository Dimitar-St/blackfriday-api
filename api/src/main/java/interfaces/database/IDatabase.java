package database;

import java.sql.Connection;

public interface IDatabase {
	public Connection getConnection();
	
	public void close();
}
