package com.example.demo.dto;

import java.io.Serializable;

public class SearchDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1891709360938570410L;

	private String skil;
	private String email;
	private String workExp;
	public String getSkil() {
		return skil;
	}
	public void setSkil(String skil) {
		this.skil = skil;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWorkExp() {
		return workExp;
	}
	public void setWorkExp(String workExp) {
		this.workExp = workExp;
	}
	public SearchDto(String skil, String email, String workExp) {
		super();
		this.skil = skil;
		this.email = email;
		this.workExp = workExp;
	}
	
	public SearchDto() {
		super();
	}
	
	
}
