package com.example.twitter.Models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUser {

	@NotEmpty(message="Either email or password doesn't match our records")
	@Email(message="Either email or password doesn't match our records")
	private String email;
	
	@NotEmpty(message="Either email or password doesn't match our records")
	@Size(min=3, max=128, message="Either email or password doesn't match our records")
	private String password;
	
	
	public LoginUser() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
