package com.nexus.nexus.exceptions;

public class EmailNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailNotFoundException(String email) {
		super("El usuario con el email: " + email + " no existe");
	}
	
}
