package com.josehinojo.springbootrefresher.clientproxy;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientProxyConfig {
	
	@Value("${users.api.url.v1}")
	private String usersEndpointUrl;
	
//	@Bean
//	public UserResourceV1 getUserResourceV1() {
//		Client client = ClientBuilder.newClient();
//		WebTarget target = client.target(usersEndpointUrl);
//		UserResourceV1 proxy = target.request().get().;
//		return proxy;
//	}
	
	
	
}
