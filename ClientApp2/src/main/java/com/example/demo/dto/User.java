package com.example.demo.dto;

import java.io.Serializable;


public class User implements Serializable {

	/**
	 * -1859485293362766653L;
	 */
	private static final long serialVersionUID = -1859485293362766653L;

	
	private String userId;
	private String email;
	private String username;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
		
}
