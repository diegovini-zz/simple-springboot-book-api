package com.springboot.simple_springboot_book_api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		String errorDescription = ex.getLocalizedMessage() != null ? ex.getLocalizedMessage() : ex.toString();
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(errorDescription);

		return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex){
		String errorDescription = ex.getLocalizedMessage() != null ? ex.getLocalizedMessage() : ex.toString();
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(errorDescription);
		return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errorDescription = ex.getLocalizedMessage() != null ? ex.getLocalizedMessage() : ex.toString();
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(errorDescription);
		return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		
	}
	

	
	
	


	
	

}
