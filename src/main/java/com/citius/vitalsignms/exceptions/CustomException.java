package com.citius.vitalsignms.exceptions;

public class CustomException extends Exception{


	private static final long serialVersionUID = 1L;
	int status;
	
	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(int status,String message) {
		super(message);
		this.status=status;
	}
}
