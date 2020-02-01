package DAOs;

import java.util.List;

import com.blackfriday.api.data.models.ProductModel;

public interface IProductDAO<T> extends DAO<T> {
	List<ProductModel> getProductsBy(String property, String value);
	
	boolean removeProduct(String id);
}
