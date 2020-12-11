package br.com.selat.exceptions;

public class ServiceValidationException extends RuntimeException {

	public ServiceValidationException() {
	}

	public ServiceValidationException(String message) {
		super(message);
	}

	public ServiceValidationException(RuntimeException e) {
		super(e);
	}
}
