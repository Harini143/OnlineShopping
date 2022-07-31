package com.revature.ecommerce.dto;

public class EcommErrorInfo {

	private String message;
	
	public EcommErrorInfo(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
