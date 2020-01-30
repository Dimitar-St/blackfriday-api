package api;

import java.sql.ResultSet;
import java.util.List;

public interface DAO<T> {
	List<T> getAll();
	
	boolean create(T obj);
	
	T processObject(ResultSet rs);
	
	void updateImage(String newImage);
}
