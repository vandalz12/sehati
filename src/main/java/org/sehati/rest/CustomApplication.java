package org.sehati.rest;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.sehati.rest.provider.AuthenticationFilter;

public class CustomApplication extends ResourceConfig {

	public CustomApplication() {
		packages("org.sehati.rest");
		register(LoggingFilter.class);
		register(AuthenticationFilter.class);
	}
	
}
