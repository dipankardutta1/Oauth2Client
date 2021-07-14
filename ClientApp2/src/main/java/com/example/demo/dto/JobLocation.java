package com.example.demo.dto;

import java.io.Serializable;
import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;





public class JobLocation implements Serializable {

	/**
	 * long serialVersionUID = 5326935925333166859L;
	 */
	private static final long serialVersionUID = 5326935925333166859L;
	
	
	private String locId;
	private String  area;
	private String  state;
	private String location;
	private boolean isCurrentLocation;
	private boolean isPrefLocation;
	private Boolean isDeleted;
	
	
		public JobLocation(String locId, String area, String state, String location, boolean isCurrentLocation,
			boolean isPrefLocation,Boolean isDeleted) {
		super();
		this.locId = locId;
		this.area = area;
		this.state = state;
		this.location = location;
		this.isCurrentLocation = isCurrentLocation;
		this.isPrefLocation = isPrefLocation;
		this.isDeleted = isDeleted;
	}


		
		public Boolean getIsDeleted() {
			return isDeleted;
		}



		public void setIsDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
		}



		public String getLocId() {
		return locId;
	}


	public void setLocId(String locId) {
		this.locId = locId;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public boolean isCurrentLocation() {
		return isCurrentLocation;
	}


	public void setCurrentLocation(boolean isCurrentLocation) {
		this.isCurrentLocation = isCurrentLocation;
	}


	public boolean isPrefLocation() {
		return isPrefLocation;
	}


	public void setPrefLocation(boolean isPrefLocation) {
		this.isPrefLocation = isPrefLocation;
	}


		public JobLocation() {
			super();
		}
	
}
