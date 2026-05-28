package com.SurveyApp.userProfile.userSignUp.model.userDTO;

import com.SurveyApp.userProfile.model.User;

public class UserSingUpDTO {
	private String name;
	private String password;
	private String email;

	public UserSingUpDTO(String name, String password, String email) {
	
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User toUser() {
		System.out.println("UserDTO is being transformed into User class");

		return new User(name, name,password, email);
	}

}
