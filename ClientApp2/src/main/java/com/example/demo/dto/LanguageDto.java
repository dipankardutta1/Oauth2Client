package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class LanguageDto implements Serializable{

	/**
	 * -6044354615820662053L;
	 */
	private static final long serialVersionUID = -6044354615820662053L;

	private String candidateId;
	private String id;
	private String language;
	private String read;
	private String write;
	private String speak;
	
	
	
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



	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}



	public String getRead() {
		return read;
	}



	public void setRead(String read) {
		this.read = read;
	}



	public String getWrite() {
		return write;
	}



	public void setWrite(String write) {
		this.write = write;
	}



	public String getSpeak() {
		return speak;
	}



	public void setSpeak(String speak) {
		this.speak = speak;
	}



	public LanguageDto(String candidateId, String id, String language, String read, String write, String speak) {
		super();
		this.candidateId = candidateId;
		this.id = id;
		this.language = language;
		this.read = read;
		this.write = write;
		this.speak = speak;
	}
	
	
	
	public LanguageDto() {
		super();
	}
	
	
	
	
}
