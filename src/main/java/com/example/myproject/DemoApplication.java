package com.example.myproject;


import java.util.Arrays;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.myproject.domain.Address;
import com.example.myproject.domain.User;


import com.example.myproject.domain.Book;
import com.example.myproject.domain.Page;


import com.example.myproject.repository.AddressRepository;
import com.example.myproject.repository.UserRepository;

import com.example.myproject.repository.BookRepository;
import com.example.myproject.repository.PageRepository;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class DemoApplication {

private static final Logger LOGGER=LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    public void one2one(UserRepository userRepository,AddressRepository addressRepository){
                // create a user instance
            User user = new User("John Doe", "john.doe@example.com", "1234abcd");

            // create an address instance
            Address address = new Address("Lake View 321", "Berlin", "Berlin",
                    "95781", "DE");

            // set child reference
            address.setUser(user);

            // set parent reference
            user.setAddress(address);

            // save the parent
            // which will save the child (address) as well
            userRepository.save(user);
            
            userRepository.delete(user);

    }

    public void one2many(BookRepository bookRepository,PageRepository pageRepository){
                
            LOGGER.info("Entering one2many...");
              // create a new book
            Book book = new Book("Java 101", "John Doe", "123456");

            Page page1=new Page(1, "Introduction contents", "Introduction", book);
            Page page2=new Page(65, "Java 8 contents", "Java 8", book);
            Page page3=new Page(95, "Concurrency contents", "Concurrency", book);

            ArrayList<Page> pages = new ArrayList<Page>();

            pages.add(page1);
            pages.add(page2);
            pages.add(page3);
            book.setPages(pages);

            // save the book
            LOGGER.info("Saving Book:{}",book);


            Book book_saved=bookRepository.save(book);
            
            LOGGER.info("Saved Book: {}",book_saved);
            LOGGER.info("Leaving one2many...");
    }
	
	@Bean
    public CommandLineRunner mappingDemo(UserRepository userRepository,
                                         AddressRepository addressRepository,
                                         BookRepository bookRepository,
                                         PageRepository pageRepository ){
        return args -> {

            one2many(bookRepository,pageRepository);
            
        };
    }
	
	
}
