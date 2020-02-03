package services;

import java.util.List;

import com.blackfriday.api.data.models.ProductModel;

public interface IProductService {
	List<ProductModel> getAll();

	ProductModel getProduct(int id);

	String addAProduct(ProductModel product);

	String removeAProduct(int id);

	String modifyAProduct(ProductModel product);
}
