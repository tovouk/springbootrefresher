package com.josehinojo.springbootrefresher.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	private final UUID userUid;
	private final String firstName;
	private final String lastName;
	private final Gender gender;
	private final int age;
	private final String email;
	
	public enum Gender{
		MALE,
		FEMALE
	}
	
	public static User newUser(UUID userUid, User user) {
		return new User(userUid,user.getFirstName(),user.getLastName(),user.getGender(),user.getAge(),user.getEmail());
	}

	public UUID getUserUid() {
		return userUid;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public User(@JsonProperty("userUid") UUID userUid,
			@JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName,
			@JsonProperty("gender") Gender gender,
			@JsonProperty("age") int age,
			@JsonProperty("email") String email) {
		super();
		this.userUid = userUid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "User [userUid=" + userUid + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", age=" + age + ", email=" + email + "]";
	}
	
}
