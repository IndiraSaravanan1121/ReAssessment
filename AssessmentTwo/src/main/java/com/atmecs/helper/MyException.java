package com.atmecs.helper;

public class MyException extends Exception {

	private static final long serialVersionUID = 1L;

	public MyException() {
	}

	public MyException(String message) {
		super(message);
	}

	public MyException(String message, Throwable cause) {
		super(message, cause);
	}

}
