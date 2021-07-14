package com.example.demo.dto;

import java.io.Serializable;
import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;





public class SalaryTypes implements Serializable {

	/**
	 * long serialVersionUID = 2433916071256261273L;
	 */
	private static final long serialVersionUID = 2433916071256261273L;
	
	
	private String  id;
	private Boolean isHourly;
	private Boolean isYearly;
	private Boolean isDeleted;
	public SalaryTypes() {
		
	}
	

	public SalaryTypes(String id, Boolean isHourly, Boolean isYearly,Boolean isDeleted) {
		
		this.id = id;
		this.isHourly = isHourly;
		this.isYearly = isYearly;
		this.isDeleted = isDeleted;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Boolean isHourly() {
		return isHourly;
	}



	public void setHourly(Boolean isHourly) {
		this.isHourly = isHourly;
	}



	public Boolean isYearly() {
		return isYearly;
	}



	public void setYearly(Boolean isYearly) {
		this.isYearly = isYearly;
	}


	public Boolean getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}



	
}
