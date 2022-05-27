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

@SpringBootApplication
public class DemoApplication {

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
            System.out.println(book);


            Book book_saved=bookRepository.save(book);
            
            System.out.println(book_saved);
            
            //book_saved.getPages().clear();
            
            //bookRepository.save(book_saved);
            
            /*Book book2 = new Book("Java 102", "SBalan", "654321");

            Page page4=new Page(1, "Demo contents", "Introduction", book);
            Page page5=new Page(65, "Java 17 8 contents", "Java 17", book);

            book2.addPage(page4);
            book2.addPage(page5);
            
            Book book2_saved=bookRepository.save(book2);
            
            System.out.println(bookRepository.findById(book2_saved.getId()));
            bookRepository.delete(book2);
            System.out.println(bookRepository.findById(book2_saved.getId()));*/
            
            

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
