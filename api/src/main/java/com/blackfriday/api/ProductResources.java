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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.blackfriday.api.DTOs.ProductDTO;
import com.blackfriday.api.data.models.ProductModel;

import services.IBoughtProductsService;
import services.IProductService;

@Path("products")
public class ProductResources {
	@Inject
	private IProductService productService;
	
	@Inject
	private IBoughtProductsService boughtProductsService;

	@Inject
	public ProductResources(IProductService productService, IBoughtProductsService boughtProductsService) {
		this.productService = productService;
		this.boughtProductsService = boughtProductsService;
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
	public Response addProduct(ProductModel product) {
		Response addProductResponse = this.productService.addAProduct(product);
		
		return addProductResponse;
	}
	
	@GET
	@PermitAll
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(@PathParam("id") int id) {
		ProductModel foundProduct = this.productService.getProduct(id);
		
		ProductDTO dto = ProductDTO.processDataModel(foundProduct);
		
		return Response.ok().entity(dto).build();
	}
	
	@DELETE
	@RolesAllowed("employee")
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeProduct(@PathParam("id") int id) {
		String message = this.productService.removeAProduct(id);
		
		return Response.ok().entity(message).build();
	}
	
	@POST
	@RolesAllowed({"employee", "client"})
	@Path("/{id}/orders")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewOrder(@PathParam("id") int productId, @Context HttpHeaders headers) {
		
		String message = null;
		int userId = Integer.parseInt(headers.getRequestHeader("user_id").get(0));
		
		boolean isCreated = this.boughtProductsService.order(userId, productId);
		
		message = isCreated ? "The order created successfully." : "Failed to make the order!";
		
		Response response = isCreated ? Response.ok().entity(message).build() : 
										Response.serverError().entity(message).build();
		
		return response;
	}
}
