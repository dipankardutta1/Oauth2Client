package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;






public class EducationEntries implements Serializable {

	/**
	 * long serialVersionUID = -3532572390177096326L;
	 */
	private static final long serialVersionUID = -3532572390177096326L;
	
				
					private String id;
					private String degree;
					private String school;
					private String fieldOfStudy;
					
					private Date startDate;
					private Date endDate;
					 private Boolean isDeleted;


					public EducationEntries(String id, String degree, String school, String fieldOfStudy,
							Date startDate, Date endDate,Boolean isDeleted) {
						super();
						this.id = id;
						this.degree = degree;
						this.school = school;
						this.fieldOfStudy = fieldOfStudy;
						this.startDate = startDate;
						this.endDate = endDate;
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



					public String getDegree() {
						return degree;
					}



					public void setDegree(String degree) {
						this.degree = degree;
					}



					public String getSchool() {
						return school;
					}



					public void setSchool(String school) {
						this.school = school;
					}



					public String getFieldOfStudy() {
						return fieldOfStudy;
					}



					public void setFieldOfStudy(String fieldOfStudy) {
						this.fieldOfStudy = fieldOfStudy;
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



					public EducationEntries() {
						 super();
					 }
}
