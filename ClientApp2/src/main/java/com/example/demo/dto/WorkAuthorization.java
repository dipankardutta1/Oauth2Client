package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;





public class WorkAuthorization implements Serializable {

	/**
	 * long serialVersionUID = 3808634459975570131L;
	 */
	private static final long serialVersionUID = 3808634459975570131L;
	
			
			private String id;
			private String visaStatus;
			private Date issueDate;
			private Date expiryDate;
			private Boolean isActive;
			
			
			private Boolean isDeleted;
			
			public WorkAuthorization() {
				super();
			}

			public WorkAuthorization(String id, String visaStatus, Date issueDate, Date expiryDate, Boolean isActive,Boolean isDeleted) {
				super();
				this.id = id;
				this.visaStatus = visaStatus;
				this.issueDate = issueDate;
				this.expiryDate = expiryDate;
				this.isActive = isActive;
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



			public String getVisaStatus() {
				return visaStatus;
			}



			public void setVisaStatus(String visaStatus) {
				this.visaStatus = visaStatus;
			}



			public Date getIssueDate() {
				return issueDate;
			}



			public void setIssueDate(Date issueDate) {
				this.issueDate = issueDate;
			}



			public Date getExpiryDate() {
				return expiryDate;
			}



			public void setExpiryDate(Date expiryDate) {
				this.expiryDate = expiryDate;
			}



			public Boolean isActive() {
				return isActive;
			}



			public void setActive(Boolean isActive) {
				this.isActive = isActive;
			}



			
}
