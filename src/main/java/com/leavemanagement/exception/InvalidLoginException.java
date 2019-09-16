package com.leavemanagement.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvalidLoginException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;
	private final String message;
	public InvalidLoginException(String message) {
		this.message = message;
	}
	
	
}
