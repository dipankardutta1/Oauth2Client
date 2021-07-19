package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

public class Candidate implements Serializable {

	/**
	 * serialVersionUID = -6924459381251323726L;
	 */
	private static final long serialVersionUID = -6924459381251323726L;

	private String candidateId;
	private String aliasName;
	private String firstName;
	private String middleName;
	private String lastName;
	private String profileTitle;
	private String interViewMode;
	private String interviewStatuses;
	private Boolean isDeleted;
	private Date birthDate;
	private String password;
	private String workExperience;
	private String releventExperience;
	private String comment;
	private String hiringType;
	private String email;
	private String alternateEmail;
	private Date createdAt;
	private Date  updatedAt;
	private String  coverLetter;
	private String  summary;

	private String  placeOfBirth;
	private String  maritalStatus;
	
	private List<Address>addresses;

	
	private List<SalaryTypes>salaryTypes;
	
	private List<Phone>phones;
	
	private List<Mobile>mobiles;


	
	private List<WorkAuthorization>workAuthorizations;

	
	

	
	private List<JobLocation>JobLocations;
	
	private List<Skills>skils;
	
	private List<EducationEntries>educationEntries;
	
	private List<ExperienceEntries>experienceEntries;
	
	private List<SocialProfiles>socialProfiles;
	
	
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
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public List<SalaryTypes> getSalaryTypes() {
		return salaryTypes;
	}
	public void setSalaryTypes(List<SalaryTypes> salaryTypes) {
		this.salaryTypes = salaryTypes;
	}
	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	public List<Mobile> getMobiles() {
		return mobiles;
	}
	public void setMobiles(List<Mobile> mobiles) {
		this.mobiles = mobiles;
	}
	public List<WorkAuthorization> getWorkAuthorizations() {
		return workAuthorizations;
	}
	public void setWorkAuthorizations(List<WorkAuthorization> workAuthorizations) {
		this.workAuthorizations = workAuthorizations;
	}
	
	public List<JobLocation> getJobLocations() {
		return JobLocations;
	}
	public void setJobLocations(List<JobLocation> jobLocations) {
		JobLocations = jobLocations;
	}
	public List<Skills> getSkils() {
		return skils;
	}
	public void setSkils(List<Skills> skils) {
		this.skils = skils;
	}
	public List<EducationEntries> getEducationEntries() {
		return educationEntries;
	}
	public void setEducationEntries(List<EducationEntries> educationEntries) {
		this.educationEntries = educationEntries;
	}
	public List<ExperienceEntries> getExperienceEntries() {
		return experienceEntries;
	}
	public void setExperienceEntries(List<ExperienceEntries> experienceEntries) {
		this.experienceEntries = experienceEntries;
	}
	public List<SocialProfiles> getSocialProfiles() {
		return socialProfiles;
	}
	public void setSocialProfiles(List<SocialProfiles> socialProfiles) {
		this.socialProfiles = socialProfiles;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	public Candidate(String candidateId, String aliasName, String firstName, String middleName, String lastName,
			String profileTitle, String interViewMode, String interviewStatuses, Date birthDate, String password,
			String workExperience, String releventExperience, String comment, String hiringType, String email,
			String alternateEmail, Date createdAt, Date updatedAt, String coverLetter, String summary,
			List<Address> addresses, List<SalaryTypes> salaryTypes, List<Phone> phones, List<Mobile> mobiles,
			List<WorkAuthorization> workAuthorizations,  List<JobLocation> jobLocations,
			List<Skills> skils, List<EducationEntries> educationEntries, List<ExperienceEntries> experienceEntries,
			List<SocialProfiles> socialProfiles) {
		super();
		this.candidateId = candidateId;
		this.aliasName = aliasName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.profileTitle = profileTitle;
		this.interViewMode = interViewMode;
		this.interviewStatuses = interviewStatuses;
		this.birthDate = birthDate;
		this.password = password;
		this.workExperience = workExperience;
		this.releventExperience = releventExperience;
		this.comment = comment;
		this.hiringType = hiringType;
		this.email = email;
		this.alternateEmail = alternateEmail;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.coverLetter = coverLetter;
		this.summary = summary;
		this.addresses = addresses;
		this.salaryTypes = salaryTypes;
		this.phones = phones;
		this.mobiles = mobiles;
		this.workAuthorizations = workAuthorizations;
		
		this.JobLocations = jobLocations;
		this.skils = skils;
		this.educationEntries = educationEntries;
		this.experienceEntries = experienceEntries;
		this.socialProfiles = socialProfiles;
	}

	public Candidate() {
		
	}
	
	
}
