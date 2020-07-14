package com.springboot.simple_springboot_book_api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.simple_springboot_book_api.controller.modelAssembler.BookModelAssembler;
import com.springboot.simple_springboot_book_api.entity.Book;
import com.springboot.simple_springboot_book_api.service.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	BookModelAssembler bookModelAssembler;

	@GetMapping(value = "/books")
	public CollectionModel<EntityModel<Book>> getBooks() {

		List<EntityModel<Book>> books = bookService.getBooks().stream().map(bookModelAssembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(books, linkTo(methodOn(BookController.class).getBooks()).withSelfRel());

	}

	@GetMapping(value = "/books/{bookId}")
	public EntityModel<Book> getBookById(@PathVariable(name = "bookId") long bookId) {

		Book book = bookService.getBookById(bookId);
		return bookModelAssembler.toModel(book);

	}

	@PostMapping(value = "/books")
	public ResponseEntity<?> addBook(@RequestBody Book book) {
		EntityModel<Book> entityModel = bookModelAssembler.toModel(bookService.addBook(book));
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@PutMapping(value = "/books")
	public EntityModel<Book> updateBook(@RequestBody Book book) {
		return bookModelAssembler.toModel(bookService.updateBook(book));	
	}

	@DeleteMapping(value = "books/{bookId}")
	public ResponseEntity<?> deleteBook(@PathVariable(name = "bookId") long bookId) {
		bookService.deleteBook(bookId);
		return ResponseEntity.noContent().build();
	}

}
