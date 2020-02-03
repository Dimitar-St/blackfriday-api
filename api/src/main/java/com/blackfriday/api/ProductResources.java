package com.blackfriday.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductModel> getAll() {
		List<ProductModel> products = this.productService.getAll();
		
		return products;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addProduct(ProductModel product) {
		String message = this.productService.addAProduct(product);
		
		return message;
	}
	
	@GET
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductModel getProduct(@PathParam("id") int id) {
		ProductModel foundProduct = this.productService.getProduct(id);
		
		return foundProduct;
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String removeProduct(@PathParam("id") int id) {
		String message = this.productService.removeAProduct(id);
		
		return message;
	}
}
