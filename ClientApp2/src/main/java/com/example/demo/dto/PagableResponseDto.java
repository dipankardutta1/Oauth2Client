package com.example.demo.dto;

import java.io.Serializable;




public class PagableResponseDto extends ResponseDto implements Serializable {

	/**
	 * long serialVersionUID = 2623767476103419992L
	 */
	private static final long serialVersionUID = 2623767476103419992L;

	
	private Integer totalPages;
	
	private Long totalItems;

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}
	
	
	
}
