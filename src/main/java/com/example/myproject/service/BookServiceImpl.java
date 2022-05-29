package com.example.myproject.service;

import org.springframework.stereotype.Service;

import java.util.List;
import com.example.myproject.domain.Book;
import com.example.myproject.domain.Page;

import com.example.myproject.repository.BookRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.*;
import java.util.Collection;
import com.example.myproject.util.DataUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service 
public class BookServiceImpl implements BookService {

private static final Logger LOGGER=LoggerFactory.getLogger(BookServiceImpl.class);


 @Autowired
 DataUtils dataUtils;

 @Autowired
 BookRepository bookRepository;
 


 public Book getBookByIsbn(String isbn) throws Exception {
    
     //Find Existing Book by isbn
    Book book= bookRepository.findByIsbn(isbn);

    //If book not found for isbn passed
     if(null==book){
         LOGGER.info("Book not found for isbn: {}",isbn);
         throw new Exception("No Records found");
     }
     LOGGER.info("Book object found: {}",book);
	 
	    return book;

 }

/* public List<Book> addPagesToBook(String isbn, List <Page> newPageList) {
     
     Book book=getBookByIsbn(isbn);
     List<Page> pageList=book.getPages();

     System.out.println("Adding New Page Object  List to DB Book Page List...");
     pageList.addAll(newPageList);
     return pageList;
     
     
 }
 public List<Page> updateBookPage(String isbn, Page page){

     Book book=getBookByIsbn(isbn);
     
     List<Page> pageList=book.getPages();
     
     Map<String,Page> pageMap=listToMap(pageList);

     pageMap.put(page.getId()+"",page);

     List<Page> pageList=mapToList(pageMap);
     
     return pageList;
}

     
 }
 public List<Page> deleteBookPages(String isbn, long pageId) {
     
     Book book=getBookByIsbn(isbn);
     
     List<Page> pageList=book.getPages();
     
     Map<String,Page> pageMap=listToMap(pageList);
     
     Page page=pageMap.remove(pageIdToDelete+"");
    
     List<Page> pageList=mapToList(pageMap);   
     
     return pageList;
    
 }
 */
 
 
 public void manageBookPages(String isbn , long pageIdToDelete,
 long pageIdToUpdate,String content,String chapter,List<Page> newPageList) throws Exception {

    LOGGER.info("Entering manageBookPages...");
    LOGGER.info("isbn: {}",isbn);
    LOGGER.info("pageIdToDelete: {}",pageIdToDelete);
    LOGGER.info("pageIdToUpdate: {}",pageIdToUpdate);
    LOGGER.info("content: {}",content);
    LOGGER.info("chapter: {}",chapter);

    LOGGER.info("Received new Page List: {}",newPageList);

    //Find Existing Book by isbn
    Book book= bookRepository.findByIsbn(isbn);

    //If book not found for isbn passed
     if(null==book){
         LOGGER.info("Book not found for isbn: {}",isbn);
         throw new Exception("No Records found");
     }
	     
	   LOGGER.info("Book object found: {}",book);

     

    LOGGER.info("Getting page collection from book object...");
    List<Page> pageList=book.getPages();
    LOGGER.info("Converting page collection List from book object TO MAP...");
    Map<String,Page> pageMap=dataUtils.listToMap(pageList);
     
     
    LOGGER.info("Deleting the Page with PageId: {}", pageIdToDelete);

     Page deletedPageObj=pageMap.remove(pageIdToDelete+"");
     
     
     LOGGER.info("Updating the Page with PageId:{}",pageIdToUpdate);

     //Update Fields Existing Page of Book
     Page pageObjToUpdate=pageMap.get(pageIdToUpdate+"");
     //Update chapter and content fields
     pageObjToUpdate.setChapter(chapter);
     pageObjToUpdate.setContent(content);
     
     pageMap.put(pageIdToUpdate+"",pageObjToUpdate);
     
     
     
     //Add New Pages to Book
     LOGGER.info("Converting Map to List...");
     pageList=dataUtils.mapToList(pageMap);

     LOGGER.info("Adding New Page Object to List...");
     pageList.addAll(newPageList);
     
     
     LOGGER.info("Clearing the Page Entity Collection in Book Entity");
     //Update page Child Collection in book
     book.getPages().clear();
     LOGGER.info("Associating the Updated Page Entity Collection to Book Entity");
     book.getPages().addAll( (ArrayList<Page>) pageList);
     
     LOGGER.info("Saving the Book with updated pages...");
     
     
     //Save the book
     Book bookUpd=bookRepository.save(book);
     LOGGER.info("Updated book with pages: {}",bookUpd);
     LOGGER.info("Leaving manageBookPages.");
     
 }
 
}