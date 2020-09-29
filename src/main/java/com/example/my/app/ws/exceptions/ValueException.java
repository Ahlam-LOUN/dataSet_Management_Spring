package com.example.my.app.ws.exceptions;

public class ValueException  extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3463965663521838959L;

	public ValueException(String message) {
		super(message);// to call the error message which is in RuntimeException
	}
	
}
