package org.fmi.tryme.admin;

public class TestProviderException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TestProviderException(String message, Throwable cause) {
		super(message, cause);
	}

	public TestProviderException(String message) {
		super(message);
	}

}
