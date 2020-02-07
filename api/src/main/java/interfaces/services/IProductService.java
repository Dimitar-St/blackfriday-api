package services;

import java.util.List;

import javax.ws.rs.core.Response;

import com.blackfriday.api.data.models.ProductModel;

public interface IProductService {
	List<ProductModel> getAll();

	ProductModel getProduct(int id);

	Response addAProduct(ProductModel product);

	String removeAProduct(int id);

	String modifyAProduct(ProductModel product);
}
