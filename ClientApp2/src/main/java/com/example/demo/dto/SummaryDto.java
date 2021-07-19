package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;



public class SummaryDto implements Serializable {


	

	/**
	 * serialVersionUID = -6924459381251323726L;
	 */
	private static final long serialVersionUID = -6192478933745032661L;
	
	private String candidateId;

	@NotBlank(message="{candidate.firstName.invalid}")
	private String firstName;
	
	
	@NotBlank(message="{candidate.lastName.invalid}")
	private String lastName;
	@NotBlank(message="{candidate.profileTitle.invalid}")
	private String profileTitle;
	
	
	@NotBlank(message = "{candidate.email.invalid}")
	@Email(message = "{candidate.email.invalid}")
	private String email;
	
	
	@NotBlank(message = "{candidate.summary.invalid}")
	private String  summary;


	public String getCandidateId() {
		return candidateId;
	}


	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getProfileTitle() {
		return profileTitle;
	}


	public void setProfileTitle(String profileTitle) {
		this.profileTitle = profileTitle;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	


	
	
	

	
	
	
}
