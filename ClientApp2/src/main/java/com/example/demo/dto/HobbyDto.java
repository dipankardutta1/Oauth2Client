package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;






public class HobbyDto implements Serializable {

	/**
	 * long serialVersionUID = -800441718403689770L;
	 */
	private static final long serialVersionUID = -800441718403689770L;
	
					@NotBlank(message="{candidate.candidateId.invalid}")
					private String candidateId;
					private String id;
					@NotBlank(message="Type Cannot be Blank")
					private String type;
						
					
					@NotBlank(message="Hobby cannot be blank")
					private String hobby;
					

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


					public String getHobby() {
						return hobby;
					}


					public void setHobby(String hobby) {
						this.hobby = hobby;
					}


					


					

					
}
