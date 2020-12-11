package br.com.selat.exceptions;

public class InternalErrorException extends RuntimeException {

	public InternalErrorException(String message) {
		super(message);
	}

	public InternalErrorException(RuntimeException e) {
		super(e);
	}
}
