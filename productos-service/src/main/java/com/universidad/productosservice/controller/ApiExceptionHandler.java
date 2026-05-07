package com.universidad.productosservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse manejarProductoNoEncontrado(RuntimeException exception) {
		return new ErrorResponse(exception.getMessage());
	}

	@ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse manejarSolicitudInvalida(Exception exception) {
		return new ErrorResponse(exception.getMessage());
	}

	public record ErrorResponse(String mensaje) {
	}
}
