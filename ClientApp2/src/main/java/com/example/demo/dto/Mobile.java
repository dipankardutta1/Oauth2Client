package com.example.demo.dto;

import java.io.Serializable;
import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;





public class Mobile implements Serializable {

	/**
	 * long serialVersionUID = 5222818996688423695L;
	 */
	private static final long serialVersionUID = 5222818996688423695L;
		
		
		private String id;
		private String countryCode;
		private String mobileNumber;
		private Boolean isDeleted;
	

		public Mobile(String id, String countryCode, String mobileNumber,Boolean isDeleted) {
			super();
			this.id = id;
			this.countryCode = countryCode;
			this.mobileNumber = mobileNumber;
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



		public String getMobileNumber() {
			return mobileNumber;
		}



		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}



		public Mobile() {
			super();
		}
}
