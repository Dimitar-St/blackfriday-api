package com.blackfriday.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.blackfriday.api.data.models.UserModel;
import services.IUserService;

@Path("user")
public class UserResource {
	private IUserService userService;

	@Inject
	public UserResource(IUserService userservice) {
		this.userService = userservice;
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public String register(UserModel user) {
		//TODO: User validation
		
		String isCreated = this.userService.register(user);
		
		return isCreated;
	}
}
