package com.example.demo.dto;

import java.io.Serializable;
import java.util.UUID;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;






public class Address implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6162695756570644637L;
	
	
	private String addressId;
	private String city;
	private String state;
	private Boolean isDeleted;
	
	
	public Address(String addressId, String city, String state,Boolean isDeleted) {
		super();
		this.addressId = addressId;
		this.city = city;
		this.state = state;
		this.isDeleted = isDeleted;
	}


	public Boolean getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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


	public Address() {
		super();
	}
}
