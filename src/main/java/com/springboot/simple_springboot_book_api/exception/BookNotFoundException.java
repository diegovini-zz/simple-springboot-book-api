package com.springboot.simple_springboot_book_api.exception;

public class BookNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookNotFoundException(Long id) {
		super("Could not find book with id "+id);
	}

}
