package com.example.demo.dto;

import java.io.Serializable;


public class WorkAuthorizationTypesMaster implements Serializable {

	/**
	 * long serialVersionUID = -1827897754085106923L;
	 */
	private static final long serialVersionUID = -1827897754085106923L;
	
			
			private String visaStatus;

			public String getVisaStatus() {
				return visaStatus;
			}

			public void setVisaStatus(String visaStatus) {
				this.visaStatus = visaStatus;
			}

			public WorkAuthorizationTypesMaster(String visaStatus) {
				super();
				this.visaStatus = visaStatus;
			}
			
			public WorkAuthorizationTypesMaster() {
				super();
			}
}
