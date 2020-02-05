package com.blackfriday.api;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<ProductModel> products = this.productService.getAll();
		
		GenericEntity<List<ProductModel>> entities = new GenericEntity<List<ProductModel>>(products){};
		
		return Response.ok(entities).build();
	}
	
	@POST
	@RolesAllowed("employee")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addProduct(ProductModel product) {
		String message = this.productService.addAProduct(product);
		
		return message;
	}
	
	@GET
	@PermitAll
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductModel getProduct(@PathParam("id") int id) {
		ProductModel foundProduct = this.productService.getProduct(id);
		
		return foundProduct;
	}
	
	@DELETE
	@RolesAllowed("employee")
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String removeProduct(@PathParam("id") int id) {
		String message = this.productService.removeAProduct(id);
		
		return message;
	}
}
