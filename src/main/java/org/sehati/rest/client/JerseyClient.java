package org.sehati.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class JerseyClient {

	public static void main(String[] args) {
//		get();
		addUser();
	}
	
	private static void get() {
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("user", "password");
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		client.register(feature);
		WebTarget webTarget = client.target("http://localhost:9080/shortenedurl/api/v1/originalLink/m");
		Response responseData = webTarget.request(MediaType.APPLICATION_JSON).get();
		
		System.out.println(responseData.readEntity(String.class));
		
	}
	
	private static void addUser() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:9080/shortenedurl/api/v1/addUser");
		MultivaluedMap<String, String> multivaluedMap = new MultivaluedHashMap<String, String>();
		Response responseData = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.form(multivaluedMap));
		
		System.out.println(responseData.readEntity(String.class));
		
	}
	
}
