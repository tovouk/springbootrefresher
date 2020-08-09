package com.josehinojo.springbootrefresher.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.ArgumentMatchers;
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
	void shouldGetAllUsers() {
		UUID annaUserUid = UUID.randomUUID();
		User anna = new User(annaUserUid,"Anna","Montana",Gender.FEMALE,30,"anna@gmail.com");
		
		List<User> users = new ArrayList<User>();
		users.add(anna);
		
		BDDMockito.given(fakeDataDao.selectAllUsers()).willReturn(users);
		
		List<User> allUsers = userService.getAllUsers(Optional.empty());
		
		assertThat(allUsers).hasSize(1);
		
		User user = allUsers.get(0);
		assertAnnaFields(user);
		
	}
	
	@Test
	void shouldGetAllUsersByGender() throws Exception{
		UUID annaUserUid = UUID.randomUUID();
		User anna = new User(annaUserUid,"Anna","Montana",Gender.FEMALE,30,"anna@gmail.com");
		
		UUID joeUserUid = UUID.randomUUID();
		User joe = new User(joeUserUid,"Joe","Jones",Gender.MALE,22,"joe.jones@gmail.com");
		
		List<User> users = new ArrayList<User>();
		users.add(anna);
		users.add(joe);
		
		BDDMockito.given(fakeDataDao.selectAllUsers()).willReturn(users);
		
		List<User> filteredUsers = userService.getAllUsers(Optional.of("female"));
		assertThat(filteredUsers).hasSize(1);
		assertAnnaFields(filteredUsers.get(0));
		
	}
	
	@Test
	void shouldThrowExceptionWhenGenderIsInvalid() {
		assertThatThrownBy(() -> userService.getAllUsers(Optional.of("asdfasdfas")))
		.isInstanceOf(IllegalStateException.class)
		.hasMessageContaining("Invalid gender");
	}

	@Test
	void shouldGetUser() {
		UUID annaUid = UUID.randomUUID();
		User anna = new User(annaUid,"Anna","Montana",Gender.FEMALE,30,"anna@gmail.com");
		
		BDDMockito.given(fakeDataDao.selectUserByUserUid(annaUid)).willReturn(Optional.of(anna));
		
		Optional<User> userOptional = userService.getUser(annaUid);
		
		assertThat(userOptional.isPresent()).isTrue();
		User user = userOptional.get();
		
		assertAnnaFields(user);
		
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
		assertAnnaFields(user);
		
		assertThat(updateResult).isEqualTo(1);
		
	}

	@Test
	void shouldRemoveUser() {
		UUID annaUid = UUID.randomUUID();
		User anna = new User(annaUid,"Anna","Montana",Gender.FEMALE,30,"anna@gmail.com");
		
		BDDMockito.given(fakeDataDao.selectUserByUserUid(annaUid)).willReturn(Optional.of(anna));
		BDDMockito.given(fakeDataDao.deleteUserByUserUid(annaUid)).willReturn(1);
				
		int deleteResult = userService.removeUser(annaUid);

		verify(fakeDataDao).selectUserByUserUid(annaUid);
		verify(fakeDataDao).deleteUserByUserUid(annaUid);
		
		assertThat(deleteResult).isEqualTo(1);
	}

	@Test
	void shouldInsertUser() {
		User anna = new User(null,"Anna","Montana",Gender.FEMALE,30,"anna@gmail.com");
		
		BDDMockito.given(fakeDataDao.insertUser(ArgumentMatchers.any(UUID.class), ArgumentMatchers.eq(anna))).willReturn(1);
		
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		
		int insertResult = userService.insertUser(anna);
		
		verify(fakeDataDao).insertUser(ArgumentMatchers.any(UUID.class), captor.capture());
		
		User user = captor.getValue();
		
		assertAnnaFields(user);
		
		assertThat(insertResult).isEqualTo(1);
	}
	
	private void assertAnnaFields(User user) {
		assertThat(user.getAge()).isEqualTo(30);
		assertThat(user.getFirstName()).isEqualTo("Anna");
		assertThat(user.getLastName()).isEqualTo("Montana");
		assertThat(user.getGender()).isEqualTo(Gender.FEMALE);
		assertThat(user.getEmail()).isEqualTo("anna@gmail.com");
		assertThat(user.getUserUid()).isNotNull();
		assertThat(user.getUserUid()).isInstanceOf(UUID.class);
	}

}
