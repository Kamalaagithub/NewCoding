package com.feignpostcommentapi.payloads;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ApiResponse 
{
	private String message;
	
	private boolean success;

	public ApiResponse() {
		
	}
	
	public ApiResponse(String message, boolean success) {
		
		this.message = message;
		this.success = success;
	}

	

	public ApiResponse(String string, boolean b, HttpStatus ok) {
	
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
