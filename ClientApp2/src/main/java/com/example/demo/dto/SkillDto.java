package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class SkillDto implements Serializable{

	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1194214911945140167L;
	private String candidateId;
	
	private String id;
	@NotBlank(message="Skill cannot be Blank")
	private String name;
	@NotBlank(message="Proficient Level cannot be blank")
	private String proficientLevel;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProficientLevel() {
		return proficientLevel;
	}
	public void setProficientLevel(String proficientLevel) {
		this.proficientLevel = proficientLevel;
	}
	

	
	
}
