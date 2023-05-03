package com.feignpostcommentapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentException extends RuntimeException
{
    
	private static final long serialVersionUId = 1L;

	public CommentException(String message) {
		super(message);
		
	}
	
	public CommentException(String message,Throwable cause) {
		super(message, cause);
		
	}
	
}
