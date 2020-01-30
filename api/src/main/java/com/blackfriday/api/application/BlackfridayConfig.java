package com.blackfriday.api.application;

import org.glassfish.jersey.server.ResourceConfig;

public class BlackfridayConfig extends ResourceConfig {
	public BlackfridayConfig() {
		 packages("com.blackfriday.api.application");
	     register(new BlackfridayApplicationBiulder());  
	}
}
