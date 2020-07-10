package com.springboot.simple_springboot_book_api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.simple_springboot_book_api.entity.Book;
import com.springboot.simple_springboot_book_api.service.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping(value = "/books")
	public List<Book> getBooks() {
		return bookService.getBooks();
	}

	@GetMapping(value = "/books/{bookId}")
	public EntityModel<Book> getBookById(@PathVariable(name = "bookId") long bookId) {

		Book book = bookService.getBookById(bookId);
		return EntityModel.of(book, //
				linkTo(methodOn(BookController.class).getBookById(bookId)).withSelfRel(),
				linkTo(methodOn(BookController.class).getBooks()).withRel("books"));
				
	}

	@PostMapping(value = "/books")
	public Book addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}

	@PutMapping(value = "/books")
	public Book updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);
	}

	@DeleteMapping(value = "books/{bookId}")
	public void deleteBook(@PathVariable(name = "bookId") long bookId) {
		bookService.deleteBook(bookId);
	}

}
