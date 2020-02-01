package services;

import java.util.List;

import com.blackfriday.api.data.models.ProductModel;

public interface IProductService {
	List<ProductModel> getAll();

	List<ProductModel> getProductsBy(String property, String value);

	String addAProduct(ProductModel product);

	String removeAProduct(ProductModel product);

	String modifyAProduct(ProductModel product);
}
