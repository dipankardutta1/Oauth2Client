package com.example.demo.dto;

import java.io.Serializable;
import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;






public class Phone implements Serializable {

	/**
	 * long serialVersionUID = -7192401132501394074L;
	 */
	private static final long serialVersionUID = -7192401132501394074L;
		
	
	private String id;
	
	
	private String countryCode;
	
	private String phoneNumber;
	private Boolean isDeleted;
	
	
	public Phone(String id, String countryCode, String phoneNumber,Boolean isDeleted) {
		super();
		this.id = id;
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
		this.isDeleted = isDeleted;
	}

	

	public Boolean getIsDeleted() {
		return isDeleted;
	}



	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}



	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCountryCode() {
		return countryCode;
	}


	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Phone() {
		super();
	}
	
}
