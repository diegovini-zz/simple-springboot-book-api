package com.springboot.simple_springboot_book_api.exception;

public class ApiErrorMessage {
	private String message;

	public ApiErrorMessage() {

	}

	public ApiErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
