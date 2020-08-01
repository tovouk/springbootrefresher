package com.josehinojo.springbootrefresher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.josehinojo.springbootrefresher.dao.FakeDataDao;
import com.josehinojo.springbootrefresher.model.User;
import com.josehinojo.springbootrefresher.model.User.Gender;

class FakeDataDaoTest {
	
	private static FakeDataDao fakeDataDao;
	
	@BeforeAll
	public static void setUp() throws Exception {
		fakeDataDao = new FakeDataDao();
	}
	
	@Test
	void shouldSelectAllUsers() {
		List<User> users = fakeDataDao.selectAllUsers();
		assertThat(users).hasSize(1);
		User user = users.get(0);
		assertThat(user.getAge()).isEqualTo(22);
		assertThat(user.getFirstName()).isEqualTo("Joe");
		assertThat(user.getLastName()).isEqualTo("Jones");
		assertThat(user.getGender()).isEqualTo(Gender.MALE);
		assertThat(user.getEmail()).isEqualTo("joe.jones@gmail.com");
		assertThat(user.getUserUid()).isNotNull();
	}

	@Test
	void shouldSelectUserByUserUid() {
		UUID annaUserUid = UUID.randomUUID();
		User user = new User(annaUserUid,"Anna","Montana",Gender.FEMALE,30,"anna@gmail.com");
		fakeDataDao.insertUser(annaUserUid, user);
		assertThat(fakeDataDao.selectAllUsers()).hasSize(2);
		Optional<User> anna = fakeDataDao.selectUserByUserUid(annaUserUid);
		
	}

	@Test
	void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteUserByUserUid() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertUser() {
		fail("Not yet implemented");
	}

}
