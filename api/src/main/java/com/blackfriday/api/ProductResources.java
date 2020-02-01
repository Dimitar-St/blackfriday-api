package com.blackfriday.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.blackfriday.api.data.models.ProductModel;

import services.IProductService;

@Path("products")
public class ProductResources {
	
	private IProductService productService;

	@Inject
	public ProductResources(IProductService productService) {
		this.productService = productService;
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductModel> getAll() {
		List<ProductModel> products = this.productService.getAll();
		
		return products;
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addProduct(ProductModel product) {
		String message = this.productService.addAProduct(product);
		
		return message;
	}
	
	@GET
	@Path("/all/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductModel> getProductByName(@PathParam("name") String nameValue ) {
		List<ProductModel> foundProductsByName = this.productService.getProductsBy("name", nameValue);
		
		return foundProductsByName;
	}
	
	@GET
	@Path("/category/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductModel> getProductByCategory(@PathParam("category") String categoryValue ) {
		List<ProductModel> foundProductsByCategory = this.productService.getProductsBy("category", categoryValue);
		
		return foundProductsByCategory;
	}
}
