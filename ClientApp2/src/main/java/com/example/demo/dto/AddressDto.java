package com.example.demo.dto;

import java.io.Serializable;


public class AddressDto implements Serializable {

	
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1745051563697574880L;
	
	private String candidateId;
	
	private String addressId;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String addressLine;
	
	
	
	
	


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getAddressLine() {
		return addressLine;
	}


	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}


	public String getCandidateId() {
		return candidateId;
	}


	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}


	

	public String getAddressId() {
		return addressId;
	}


	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	
}
