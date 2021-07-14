package com.example.demo.dto;

import java.io.Serializable;
import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;





public class Skills implements Serializable {

	/**
	 * long serialVersionUID = -5919650331873952350L;
	 */
	private static final long serialVersionUID = -5919650331873952350L;
	
					
					private String id;
					private String name;
					private String proficientLevel;
					private Boolean isDeleted;
					
				

					public Skills(String id, String name, String proficientLevel,Boolean isDeleted) {
						super();
						this.id = id;
						this.name = name;
						this.proficientLevel = proficientLevel;
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



					public Skills() {
						super();
					}
}
