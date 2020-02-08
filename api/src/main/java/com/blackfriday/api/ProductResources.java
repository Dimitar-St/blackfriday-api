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

	private IProductService productService;
	private IBoughtProductsService boughtProductsService;
	
	private static final String ORDER_CREATED = "The order created successfully.";
	private static final String FALIED_TO_MAKE_ORDER = "Failed to make the order!";

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
		
		return Response.ok().entity(products).build();
	}
	
	@POST
	@RolesAllowed("employee")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProduct(ProductDTO productDto) {
		
		ProductModel product = ProductModel.processObject(productDto);
		
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
		int userId = Integer.parseInt(headers.getRequestHeader("user_id").get(0));
		
		boolean isCreated = this.boughtProductsService.order(userId, productId);
		
		Response response = isCreated ? Response.ok().entity(ORDER_CREATED).build() : 
										Response.serverError().entity(FALIED_TO_MAKE_ORDER).build();
		
		return response;
	}
}
