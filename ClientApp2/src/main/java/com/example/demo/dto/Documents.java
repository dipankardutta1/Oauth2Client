package com.example.demo.dto;


import java.io.Serializable;

import org.bson.types.Binary;





public class Documents implements Serializable {

	/**
	 * long serialVersionUID = -6093958153859330449L;
	 */
	private static final long serialVersionUID = -6093958153859330449L;
			
		
			private String title;
			private Binary image;
			private String fileName;
			private Boolean isDeleted;
			
		
			public Documents(String title, Binary image,Boolean isDeleted) {
				super();
				this.title = title;
				this.image = image;
				this.isDeleted = isDeleted;
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


			public String getTitle() {
				return title;
			}


			public void setTitle(String title) {
				this.title = title;
			}


			public Binary getImage() {
				return image;
			}


			public void setImage(Binary image) {
				this.image = image;
			}


			public Documents() {
				super();
			}
			
			
			
}
