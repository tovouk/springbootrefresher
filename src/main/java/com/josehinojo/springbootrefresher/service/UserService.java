package com.josehinojo.springbootrefresher.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josehinojo.springbootrefresher.dao.UserDao;
import com.josehinojo.springbootrefresher.model.User;

@Service
public class UserService {
	
	private UserDao userDao;
	
	@Autowired
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getAllUsers() {
		return userDao.selectAllUsers();
	}

	public Optional<User> getUser(UUID userUid) {
		return userDao.selectUserByUserUid(userUid);
	}


	public int updateUser(User user) {
		Optional<User> optionalUser = getUser(user.getUserUid());
		if(optionalUser.isPresent()) {
			userDao.updateUser(user);
			return 1;
		}
		return -1;
	}

	public int removeUser(UUID userUid) {
		Optional<User> optionalUser = getUser(userUid);
		if(optionalUser.isPresent()) {
			userDao.deleteUserByUserUid(userUid);
			return 1;
		}
		return -1;
	}

	public int insertUser(User user) {
		//not correct, the UUID should already be present for the user
		return userDao.insertUser(UUID.randomUUID(),  user);
	}
}
