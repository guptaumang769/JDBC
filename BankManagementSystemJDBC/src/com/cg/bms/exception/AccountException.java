package com.cg.bms.exception;

public class AccountException extends Exception {

	private static final long serialVersionUID = 1L; 

	private String message;

	public AccountException(String message) {
		super();
		this.message = message;
	}

}
