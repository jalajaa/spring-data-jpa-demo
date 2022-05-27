package com.example.myproject.repository;

import com.example.myproject.domain.Book;
import com.example.myproject.domain.Page;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PageRepository extends CrudRepository<Page, Long> {

    List<Page> findByBook(Book book, Sort sort);
}