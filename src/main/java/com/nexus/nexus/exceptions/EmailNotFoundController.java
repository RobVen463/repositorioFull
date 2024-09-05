package com.nexus.nexus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//Para indicar que esta clase es un indicador de Exceptions agrego la anotación @ControllerAdvice
@ControllerAdvice
public class EmailNotFoundController {

	//La clase recibe anotaciones que nos permite controlar el método de la Exception y nos permite aplicarlo en métodos de tipo Entity
	//Método para manejar la Exception y que retorna un mensaje (.getMessage)
	@ResponseBody
	@ExceptionHandler(EmailNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String EmailNotFoundHandler(EmailNotFoundException e) {
		return e.getMessage() ;
	}
	
}
