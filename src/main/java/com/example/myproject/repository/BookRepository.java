package com.example.myproject.repository;

import com.example.myproject.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

    Book findByIsbn(String isbn);
}