package com.josehinojo.springbootrefresher.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;

import com.josehinojo.springbootrefresher.dao.FakeDataDao;
import com.josehinojo.springbootrefresher.model.User;
import com.josehinojo.springbootrefresher.model.User.Gender;

class UserServiceTest {
	
	@Mock
	private FakeDataDao fakeDataDao;
	private UserService userService;

	@BeforeEach
	void setUpBeforeClass() throws Exception {
		MockitoAnnotations.initMocks(this);
		userService = new UserService(fakeDataDao);
	}

	@Test
	void testUserService() {
		fail("Not yet implemented");
	}

	@Test
	void shouldGetAllUsers() {
		UUID annaUserUid = UUID.randomUUID();
		User anna = new User(annaUserUid,"Anna","Montana",Gender.FEMALE,30,"anna@gmail.com");
		
		List<User> users = new ArrayList<User>();
		users.add(anna);
		
		BDDMockito.given(fakeDataDao.selectAllUsers()).willReturn(users);
		
		List<User> allUsers = userService.getAllUsers();
		
		assertThat(allUsers).hasSize(1);
		
		User user = allUsers.get(0);
		assertUserFields(user);
		
	}

	@Test
	void shouldGetUser() {
		UUID annaUid = UUID.randomUUID();
		User anna = new User(annaUid,"Anna","Montana",Gender.FEMALE,30,"anna@gmail.com");
		
		BDDMockito.given(fakeDataDao.selectUserByUserUid(annaUid)).willReturn(Optional.of(anna));
		
		Optional<User> userOptional = userService.getUser(annaUid);
		
		assertThat(userOptional.isPresent()).isTrue();
		User user = userOptional.get();
		
		assertUserFields(user);
		
	}

	@Test
	void shouldUpdateUser() {
		UUID annaUid = UUID.randomUUID();
		User anna = new User(annaUid,"Anna","Montana",Gender.FEMALE,30,"anna@gmail.com");
		
		BDDMockito.given(fakeDataDao.selectUserByUserUid(annaUid)).willReturn(Optional.of(anna));
		BDDMockito.given(fakeDataDao.updateUser(anna)).willReturn(1);
		
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		
		int updateResult = userService.updateUser(anna);
		
		verify(fakeDataDao).selectUserByUserUid(annaUid);
		verify(fakeDataDao).updateUser(captor.capture());
		
		User user = captor.getValue();
		assertUserFields(user);
		
		assertThat(updateResult).isEqualTo(1);
		
	}

	@Test
	void testRemoveUser() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertUser() {
		fail("Not yet implemented");
	}
	
	private void assertUserFields(User user) {
		assertThat(user.getAge()).isEqualTo(30);
		assertThat(user.getFirstName()).isEqualTo("Anna");
		assertThat(user.getLastName()).isEqualTo("Montana");
		assertThat(user.getGender()).isEqualTo(Gender.FEMALE);
		assertThat(user.getEmail()).isEqualTo("anna@gmail.com");
		assertThat(user.getUserUid()).isNotNull();
	}

}
