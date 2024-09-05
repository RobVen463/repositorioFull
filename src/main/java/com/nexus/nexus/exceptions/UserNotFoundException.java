package com.nexus.nexus.exceptions;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	//Método constructor va a recibir un parámetro para poder evaluar y lanzar la Exception
	public UserNotFoundException(Long id) {
		super("El usuario con el id: " + id + " no existe.");
	}
	
}
