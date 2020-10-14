package com.navinfo.atmapi.exceptions;

public class InsufficientBalanceException extends Exception{
	
	public InsufficientBalanceException() {
		super();
	}
	
	public InsufficientBalanceException(String message) {
		super(message);
	}
}
