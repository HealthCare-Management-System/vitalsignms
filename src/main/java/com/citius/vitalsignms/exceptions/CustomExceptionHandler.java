package com.citius.vitalsignms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	private ResponseEntity<ErrorResponse> handleUSerNotFountException(UserNotFoundException ex) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(111, ex.getMessage()));
	}

	@ExceptionHandler(CustomException.class)
	private ResponseEntity<ErrorResponse> handleAllException(CustomException ex) {

		return ResponseEntity.status(ex.status).body(new ErrorResponse(ex.status, ex.getMessage()));
	}

}
