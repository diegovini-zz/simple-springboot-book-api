package com.springboot.simple_springboot_book_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.simple_springboot_book_api.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
