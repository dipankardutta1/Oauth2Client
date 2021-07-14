package com.example.demo.dto;

import java.io.Serializable;
import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;






public class SocialProfiles implements Serializable {

	/**
	 * long serialVersionUID = -800441718403689770L;
	 */
	private static final long serialVersionUID = -800441718403689770L;
	
					
					private String id;
					private String type;
					private String userName;
					private String url;
					private Boolean isDeleted;

					public SocialProfiles(String id, String type, String userName, String url,Boolean isDeleted) {
						super();
						this.id = id;
						this.type = type;
						this.userName = userName;
						this.url = url;
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


					public SocialProfiles() {
						super();
					}
}
