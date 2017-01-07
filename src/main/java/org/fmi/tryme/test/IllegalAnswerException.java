package org.fmi.tryme.test;

public class IllegalAnswerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IllegalAnswerException(String message) {
		super(message);
	}

}
