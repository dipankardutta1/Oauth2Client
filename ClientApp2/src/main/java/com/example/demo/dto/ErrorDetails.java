package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ErrorDetails implements Serializable {
	 /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7955245640326121833L;
		private Date timestamp;
	  private String field;
	  private String details;
	public ErrorDetails(Date timestamp, String field, String details) {
		super();
		this.timestamp = timestamp;
		this.field = field;
		this.details = details;
	}
	
	public ErrorDetails() {
		super();
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	  
	
	  
}
