package com.josehinojo.springbootrefresher.clientproxy;

import javax.ws.rs.client.ClientBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientProxyConfig {
	
	@Bean
	public UserResourceV1 getUserResourceV1() {
		ResteasyClient client = (ResteasyClient)ClientBuilder.newClient();
		final String usersEndpointUrl = "http://localhost:8080/api/v1/users";
		ResteasyWebTarget target = client.target(usersEndpointUrl);
		UserResourceV1 proxy = target.proxy(UserResourceV1.class);
		return proxy;
	}
	
}
