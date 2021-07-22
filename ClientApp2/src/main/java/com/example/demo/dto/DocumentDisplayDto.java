package com.example.demo.dto;

import java.io.Serializable;

public class DocumentDisplayDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2123469796206357298L;
	
	private String title;
	private String image;
	private String fileName;
	private Boolean isDeleted;
	private String type;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
