package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;





public class MobileDto implements Serializable {

	/**
	 * long serialVersionUID = 5222818996688423695L;
	 */
	private static final long serialVersionUID = 5222818996688423695L;
		
		
		private String candidateId;
		private String id;
		@NotBlank(message="Country Code cannot be Blank")
		@Size(min=2, max=3,message="Invlid Country Code")
		private String countryCode;
		@NotBlank(message="Mobile Number cannot be Blank")
		@Size(min=5, max=20,message="Invalid Mobile Number")
		private String mobileNumber;
		
	
		public String getCandidateId() {
			return candidateId;
		}



		public void setCandidateId(String candidateId) {
			this.candidateId = candidateId;
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



		
}
