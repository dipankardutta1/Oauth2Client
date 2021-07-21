package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;






public class SocialProfileDto implements Serializable {

	/**
	 * long serialVersionUID = -800441718403689770L;
	 */
	private static final long serialVersionUID = -800441718403689770L;
	
					@NotBlank(message="{candidate.candidateId.invalid}")
					private String candidateId;
					private String id;
					@NotBlank(message="{candidate.socialProfiles.type.invalid}")
					private String type;
					@NotBlank(message="{candidate.socialProfiles.userName.invalid}")	
					private String userName;
					@NotBlank(message="{candidate.socialProfiles.url.invalid}")
					private String url;
					

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


					public String getType() {
						return type;
					}


					public void setType(String type) {
						this.type = type;
					}


					public String getUserName() {
						return userName;
					}


					public void setUserName(String userName) {
						this.userName = userName;
					}


					public String getUrl() {
						return url;
					}


					public void setUrl(String url) {
						this.url = url;
					}


					
}
