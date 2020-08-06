package com.josehinojo.springbootrefresher.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.josehinojo.springbootrefresher.model.User;
import com.josehinojo.springbootrefresher.service.UserService;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserResource {

	private UserService userService;
	
	@Autowired
	public UserResource(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "hello")
	public List<User> fetchUsers(){
		return userService.getAllUsers();
	}
	
}
