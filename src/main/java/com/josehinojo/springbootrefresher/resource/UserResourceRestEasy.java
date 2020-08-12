package com.josehinojo.springbootrefresher.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.josehinojo.springbootrefresher.model.User;
import com.josehinojo.springbootrefresher.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	
	@GET
	@Path("{userUid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchUser(@PathParam("userUid") UUID userUid) {
		Optional<User> user = userService.getUser(userUid);
		if(user.isPresent()) {
			return Response.ok(user.get()).build();
		} else {
			return Response.status(Status.NOT_FOUND)
			.entity(new ErrorMessage("user " + userUid + " was not found."))
			.build();
		}
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertUser(User user) {
		return getIntegerResponse(userService.insertUser(user));
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(User user) {
		return getIntegerResponse(userService.updateUser(user));
	}
	
	@DELETE
	@Path("{userUid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("userUid") UUID userUid) {
		return getIntegerResponse(userService.removeUser(userUid));
	}
	
	
	private Response getIntegerResponse(int result) {
		if(result == 1) {
			return Response.ok().build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
}
