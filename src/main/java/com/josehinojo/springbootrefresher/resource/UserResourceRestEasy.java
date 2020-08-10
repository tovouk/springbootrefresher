package com.josehinojo.springbootrefresher.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josehinojo.springbootrefresher.model.User;
import com.josehinojo.springbootrefresher.service.UserService;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Component
@Path("/api/v1/users")
public class UserResourceRestEasy {
	
	private UserService userService;
	
	@Autowired
	public UserResourceRestEasy(UserService userService) {
		this.userService = userService;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> fetchUsers(@QueryParam("gender") String gender){
		return userService.getAllUsers(Optional.ofNullable(gender));
	}
	
}
