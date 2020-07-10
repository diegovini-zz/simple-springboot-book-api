package com.springboot.simple_springboot_book_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.simple_springboot_book_api.entity.Book;
import com.springboot.simple_springboot_book_api.exception.BookNotFoundException;
import com.springboot.simple_springboot_book_api.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;

	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	public Book getBookById(Long bookId) {
		return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
	}

	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	public Book updateBook(Book updatedBook) {
		return bookRepository.findById(updatedBook.getId()).map(book -> {
			book.setName(updatedBook.getName());
			book.setPrice(updatedBook.getPrice());
			return bookRepository.save(book);
		}).orElseGet(() -> {
			return bookRepository.save(updatedBook);
		});
	}

	public void deleteBook(Long bookId) {
		getBookById(bookId);
		bookRepository.deleteById(bookId);
	}
}
