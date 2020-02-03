package database;

import java.sql.Connection;

import javax.persistence.EntityManager;

public interface IDatabase {
	EntityManager createEntityManager();
}
