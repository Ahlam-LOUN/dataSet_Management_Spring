package com.example.my.app.ws.exceptions;

public class UserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1650250431586577483L;

	public UserException(String message) {
		super(message);// to call the error message which is in RuntimeException
	}
	

}
