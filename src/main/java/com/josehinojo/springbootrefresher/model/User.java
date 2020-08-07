package com.josehinojo.springbootrefresher.model;

import java.util.UUID;

public class User {
	
	private UUID userUid;
	private String firstName;
	private String lastName;
	private Gender gender;
	private int age;
	private String email;
	
	public enum Gender{
		MALE,
		FEMALE
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
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserUid(UUID userUid2) {
		this.userUid = userUid2;
	}
	
	public User() {
		
	}

	public User(UUID userUid, String firstName, String lastName, Gender gender, int age, String email) {
		super();
		this.userUid = userUid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.email = email;
	}
	
	public User(String firstName, String lastName, Gender gender, int age, String email) {
		super();
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
