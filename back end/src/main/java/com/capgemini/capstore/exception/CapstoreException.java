package com.capgemini.capstore.exception;

public class CapstoreException extends RuntimeException{

	String message ;

	public CapstoreException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
