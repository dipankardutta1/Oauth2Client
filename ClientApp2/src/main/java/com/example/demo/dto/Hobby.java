package com.example.demo.dto;

import java.io.Serializable;





public class Hobby implements Serializable {

	/**
	 * long serialVersionUID = -800441718403689770L;
	 */
	private static final long serialVersionUID = -800441718403689770L;
	
					
					private String id;
					private String type;
					
					private String hobby;
					private Boolean isDeleted;
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
					public Boolean getIsDeleted() {
						return isDeleted;
					}
					public void setIsDeleted(Boolean isDeleted) {
						this.isDeleted = isDeleted;
					}

					


					
}
