package com.blackfriday.api.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.blackfriday.api.services.AuthenticationFilter;

public class BlackfridayConfig extends ResourceConfig {
	public BlackfridayConfig() {
		 packages("com.blackfriday.api.application");
	     register(new BlackfridayApplicationBuilder());  
	     
	     //register(CORSResponseFilter.class);
	     //register(AuthenticationFilter.class);
	     
	}
}
