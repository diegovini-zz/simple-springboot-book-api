package com.springboot.simple_springboot_book_api.controller.modelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.springboot.simple_springboot_book_api.controller.BookController;
import com.springboot.simple_springboot_book_api.entity.Book;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<Book, EntityModel<Book>>{

	@Override
	public EntityModel<Book> toModel(Book book) {
		
		return EntityModel.of(book,linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel(),
				linkTo(methodOn(BookController.class).getBooks()).withRel("books"));
	}
	

}
