package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;



public class CandidateDto implements Serializable {




	/**
	 * serialVersionUID = -6924459381251323726L;
	 */
	private static final long serialVersionUID = -6192478933745032661L;

	private String candidateId;


	private String aliasName;


	private String firstName;

	private String middleName;

	private String lastName;

	private String profileTitle;

	private String interViewMode;

	private String interviewStatuses;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private Date birthDate;

	private String password;

	private String workExperience;
	private String releventExperience;
	private String comment;
	private String hiringType;

	private String email;

	private String alternateEmail;
	private String  coverLetter;
	private String  summary;

	private String  placeOfBirth;
	private String  maritalStatus;





	//setter and getter

	public String getCandidateId() {
		return candidateId;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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
	public String getInterViewMode() {
		return interViewMode;
	}
	public void setInterViewMode(String interViewMode) {
		this.interViewMode = interViewMode;
	}
	public String getInterviewStatuses() {
		return interviewStatuses;
	}
	public void setInterviewStatuses(String interviewStatuses) {
		this.interviewStatuses = interviewStatuses;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}
	public String getReleventExperience() {
		return releventExperience;
	}
	public void setReleventExperience(String releventExperience) {
		this.releventExperience = releventExperience;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getHiringType() {
		return hiringType;
	}
	public void setHiringType(String hiringType) {
		this.hiringType = hiringType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAlternateEmail() {
		return alternateEmail;
	}
	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}
	public String getCoverLetter() {
		return coverLetter;
	}
	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}











}
