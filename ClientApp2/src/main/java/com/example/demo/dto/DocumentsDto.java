package com.example.demo.dto;


import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;





public class DocumentsDto implements Serializable {

	
		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = -90945537767349072L;
	
	
		@NotBlank
		private String candidateId;
		private String title;
		@NotNull
		private MultipartFile image;
		
		private String fileName;
		private String type;
		
	
		
		

		


		public String getType() {
			return type;
		}


		public void setType(String type) {
			this.type = type;
		}


		public String getFileName() {
			return fileName;
		}


		public void setFileName(String fileName) {
			
			this.fileName = image.getOriginalFilename();
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


		public MultipartFile getImage() {
			return image;
		}


		public void setImage(MultipartFile image) {
			this.fileName = image.getOriginalFilename();
			this.image = image;
		}


		


			
			
}
