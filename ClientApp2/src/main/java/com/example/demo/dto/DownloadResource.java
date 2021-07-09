package com.example.demo.dto;

import java.io.Serializable;

import org.springframework.core.io.Resource;

public class DownloadResource implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6184218780360870982L;
	
	
	private Resource resource;
	private String candidateId;
	private String title;
	private String fileName;
	
	

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}
