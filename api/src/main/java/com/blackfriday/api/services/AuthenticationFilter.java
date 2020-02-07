package com.blackfriday.api.services;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.blackfriday.api.data.models.UserModel;

import services.IUserService;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
	@Context
	private ResourceInfo resourceInfo;
	
	@Inject
	private IUserService service;

	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";
	private static final String AUTHENTICATION_BEARER = "Bearer";
	

	
	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		Method method = resourceInfo.getResourceMethod();
		if (!method.isAnnotationPresent(PermitAll.class)) {
			if (method.isAnnotationPresent(DenyAll.class)) {
				request.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Access blocked for all users !!").build());
				return;
			}
			

			final MultivaluedMap<String, String> headers = request.getHeaders();

			final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

			if (authorization == null || authorization.isEmpty()) {
				request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
						.entity("You cannot access this resource").build());
				return;
			}
			
			String authorizationHeader = request.getHeaderString(HttpHeaders.AUTHORIZATION);
			
			if(authorizationHeader.isEmpty() || authorizationHeader == null) {
				request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
					   .entity("No token provided")
					   .build());
			}
			
			
			String id = null;	
			String token = authorizationHeader.substring(this.AUTHENTICATION_BEARER.length()).trim();

		    id = SecurityToken.validateJwtToken(token);
			
			UserModel user = service.getUserById(Integer.parseInt(id));
			
			if(!user.getToken().equals(token)) {
				request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
					   .entity("Token Expired")
					   .build());
			}
			
			if (method.isAnnotationPresent(RolesAllowed.class)) {
				RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
				Set<String>rolesSet =  new HashSet<String>(Arrays.asList(rolesAnnotation.value()));;
				
				if (!isUserAllowed(user, rolesSet)) {
					request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
							.entity("You cannot access this resource").build());
					return;
				}
			}
			
			List<String> idList = new ArrayList<String>();
			
			idList.add(id);
			headers.put("user_id", idList);
		}
	}

	private boolean isUserAllowed(final UserModel user, final Set<String> rolesSet) {
		boolean isAllowed = false;

	    if (rolesSet.contains(user.getRole())) {
			isAllowed = true;
		}
			
		return isAllowed;
	}

}
