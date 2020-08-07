package com.josehinojo.springbootrefresher.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(method = RequestMethod.GET)
	public List<User> fetchUsers(){
		return userService.getAllUsers();
	}
	
	@RequestMapping(
		method = RequestMethod.GET,
		path = "{userUid}"
	)
	public ResponseEntity<?> fetchUser(@PathVariable("userUid") UUID userUid) {
		return userService.getUser(userUid).<ResponseEntity<?>>map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ErrorMessage("user " + userUid + " was not found.")));
	}
	
	//Only Accepts JSON values from client
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> insertNewUser(@RequestBody User user) {
		int result = userService.insertUser(user);
		return getIntegerResponse(result);
	}
	
	@RequestMapping(method =  RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> updateUser(@RequestBody User user){
		int result = userService.updateUser(user);
		return getIntegerResponse(result);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,
			path = "{userUid}")
	public ResponseEntity<Integer> deleteUser(@PathVariable("userUid") UUID userUid){
		int result = userService.removeUser(userUid);
		return getIntegerResponse(result);
	}
	
	private ResponseEntity<Integer> getIntegerResponse(int result) {
		if(result == 1) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	class ErrorMessage {
		String errorMessage;

		public ErrorMessage(String errorMessage) {
			super();
			this.errorMessage = errorMessage;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
	}
}
