package com.springboot.simple_springboot_book_api.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler  {

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
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		//ApiErrorMessage apiErrorMessage = new ApiErrorMessage("Invalid arguments");
		Map<String,String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>("erro", new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>("erro", new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	

	
	
	


	
	

}
