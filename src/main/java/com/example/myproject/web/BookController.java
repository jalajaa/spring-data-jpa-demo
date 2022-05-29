package com.example.myproject.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.myproject.repository.BookRepository;
import com.example.myproject.domain.Book;
import com.example.myproject.domain.Page;


import com.example.myproject.service.BookService;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import java.util.HashMap;

import java.io.*;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class BookController {

	private static final Logger LOGGER=LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	BookRepository bookRepository;


	@Autowired
	BookService bookService;


	@GetMapping("/addPageToBook/{isbn}")
	public void addPageToBook(@PathVariable(value = "isbn") String isbn)  throws Exception { 
	    
	    LOGGER.info("Entering addPageToBook...");

	    //Find Existing Book by isbn
	    Book book= bookRepository.findByIsbn(isbn);
	    
	    //If book not found for isbn passed
	     if(null==book){
	         LOGGER.info("Book not found for isbn: {}",isbn);
	         throw new Exception("No Records found");
	     }
	     
	     LOGGER.info("Book found : {}",book);
	     
	     //Add a New Page to the retrieved book 
	     book.getPages().add(new Page(555, "Let US C", "EndNote", book) );
	     
	     LOGGER.info("Adding new page to book...");
	     
	     
	     
	     //Save the updated book with new page
	     bookRepository.save(book);
	     
      LOGGER.info("Added new page to book...");
      LOGGER.info("Leaving addPageToBook");
         
	}
	
@GetMapping("/updateParent/{isbn}")
public void updateParent(
 @PathVariable(value = "isbn") String isbn ,
 @RequestParam(value = "pageIdToDelete") long pageIdToDelete,
 @RequestParam(value = "pageIdToUpdate") long pageIdToUpdate,
 @RequestParam(value = "content") String content, 
 @RequestParam(value = "chapter") String chapter )  throws Exception { 

 LOGGER.info("Entering updateParent...");  
 Book book= bookRepository.findByIsbn(isbn);

    //If book not found for isbn passed
     if(null==book){
         LOGGER.info("Book not found for isbn: {}",isbn);
         throw new Exception("No Records found");
     }
       
  
  Page pagen1=new Page(111, "Introduction contents", "Introduction", book);
  Page pagen2=new Page(222, "Summary contents", "Ending", book);
     
  List<Page> newPageList=new ArrayList<Page>();
  newPageList.add(pagen1);
  newPageList.add(pagen2);
  
  bookService.manageBookPages(isbn ,pageIdToDelete, pageIdToUpdate,content,chapter,newPageList);
  
  LOGGER.info("Leaving updateParent.");
   
}


	
}