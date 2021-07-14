package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;








public class ExperienceEntries implements Serializable {

	/**
	 * long serialVersionUID = 7046660823849109067L;
	 */
	private static final long serialVersionUID = 7046660823849109067L;
					
				
					private String id;
					private String title;
					private String summary;
					
					private Date startDate;
					
					private Date endDate;
					private String company;
					private String industry;
					private Boolean isCurrent;
					private Boolean isDeleted;
					
				

					public ExperienceEntries(String id, String title, String summary, Date startDate, Date endDate,
							String company, String industry, Boolean isCurrent,Boolean isDeleted) {
						super();
						this.id = id;
						this.title = title;
						this.summary = summary;
						this.startDate = startDate;
						this.endDate = endDate;
						this.company = company;
						this.industry = industry;
						this.isCurrent = isCurrent;
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



					public Boolean isCurrent() {
						return isCurrent;
					}



					public void setCurrent(Boolean isCurrent) {
						this.isCurrent = isCurrent;
					}



					public ExperienceEntries() {
						super();
					}
					
}
