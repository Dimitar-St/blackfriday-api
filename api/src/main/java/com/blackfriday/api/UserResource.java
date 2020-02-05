package com.blackfriday.api;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
		String isCreated = this.userService.register(user);
		
		return Response.ok().entity(isCreated).build();
	}
	
	@POST
	@PermitAll
	@Path("/auth")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserModel login(UserModel userToLogIn) {
		UserModel loggedUser = this.userService.login(userToLogIn);
		
		if(loggedUser == null) {
			return new UserModel();
		}
		
		return loggedUser;
	}
}
