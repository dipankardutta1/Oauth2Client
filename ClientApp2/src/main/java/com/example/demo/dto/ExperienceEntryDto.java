package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;







public class ExperienceEntryDto implements Serializable {

	/**
	 * long serialVersionUID = 7046660823849109067L;
	 */
	private static final long serialVersionUID = 7046660823849109067L;
					
					private String candidateId;
					private String id;
					private String title;
					private String summary;
					@DateTimeFormat(pattern = "MM/dd/yyyy")
					@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
					private Date startDate;
					@DateTimeFormat(pattern = "MM/dd/yyyy")
					@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
					private Date endDate;
					private String company;
					private String industry;
					private Boolean isCurrent;
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
					public String getTitle() {
						return title;
					}
					public void setTitle(String title) {
						this.title = title;
					}
					public String getSummary() {
						return summary;
					}
					public void setSummary(String summary) {
						this.summary = summary;
					}
					public Date getStartDate() {
						return startDate;
					}
					public void setStartDate(Date startDate) {
						this.startDate = startDate;
					}
					public Date getEndDate() {
						return endDate;
					}
					public void setEndDate(Date endDate) {
						this.endDate = endDate;
					}
					public String getCompany() {
						return company;
					}
					public void setCompany(String company) {
						this.company = company;
					}
					public String getIndustry() {
						return industry;
					}
					public void setIndustry(String industry) {
						this.industry = industry;
					}
					public Boolean getIsCurrent() {
						return isCurrent;
					}
					public void setIsCurrent(Boolean isCurrent) {
						this.isCurrent = isCurrent;
					}
					
					
}
