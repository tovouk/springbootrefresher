package com.josehinojo.springbootrefresher.clientproxy;

import java.util.List;
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

import com.josehinojo.springbootrefresher.model.User;

public interface UserResourceV1 {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<User> fetchUsers(@QueryParam("gender") String gender);
	
	@GET
	@Path("{userUid}")
	@Produces(MediaType.APPLICATION_JSON)
	Response fetchUser(@PathParam("userUid") UUID userUid);
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response insertUser(User user);
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response updateUser(User user);
	
	@DELETE
	@Path("{userUid}")
	@Produces(MediaType.APPLICATION_JSON)
	Response deleteUser(@PathParam("userUid") UUID userUid);
	
}
