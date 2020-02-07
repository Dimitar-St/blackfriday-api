package com.blackfriday.api;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.blackfriday.api.DTOs.Token;
import com.blackfriday.api.DTOs.UserDTO;
import com.blackfriday.api.data.models.UserModel;

import passwordgenerater.IPasswordEncryptionAndDecryptionGenerater;
import services.IUserService;

@Path("users")
public class UserResource {
	private IUserService userService;

	@Inject
	public UserResource(IUserService userservice) {
		this.userService = userservice;
	}
	
	@POST
	@PermitAll
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(UserModel user) {	
		Response isCreated = this.userService.register(user);

		return isCreated;
	}
	
	@POST
	@PermitAll
	@Path("/auth")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UserModel userToLogIn) {
		Response responseToken = this.userService.login(userToLogIn);
		
		return responseToken;
	}
	
	@GET
	@Path("/{id}")
	@RolesAllowed({"employee", "client"})
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response userProfile(@PathParam("id") int userId,  @Context HttpHeaders headers) {
		
		int loggedUserId = Integer.parseInt(headers.getRequestHeader("user_id").get(0));
		
		if(loggedUserId != userId) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
		
		UserModel user = this.userService.getUserById(userId);
		
		UserDTO dto = UserDTO.processDataModel(user);
		
		return Response.ok().entity(dto).build();
	}
}
