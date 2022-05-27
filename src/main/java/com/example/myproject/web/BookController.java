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

import java.util.Optional;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import java.util.HashMap;

import java.io.*;
import java.util.Collection;

@RestController
public class BookController {

	private static final String template = "Hello, %s!";
	
	@Autowired
	BookRepository bookRepository;


    public java.util.Map listToMap(List<Page> pageList){
        System.out.println("Entering listToMap");
        Map<String, Page> map = new HashMap<>();
  
        // put every value list to Map
        for (Page page : pageList) {
            map.put(page.getId() + "", page);
        }
  
        // print map
        System.out.println("Map  : " + map);
        System.out.println("Leaving listToMap");
        return map;
        
    }
    
    public ArrayList<Page> mapToList( Map<String, Page> map){
        System.out.println("Entering mapToList");
        
        
         // Using Collection
        Collection<Page> val = map.values();
 
        // Creating an ArrayList
        ArrayList<Page> pageList = new ArrayList<Page>(val);
  
        // print List
        System.out.println("pageList  : " + pageList);
        System.out.println("Leaving mapToList");
        return pageList;
        
    }

	@GetMapping("/addPageToBook/{isbn}")
	public void addPageToBook(@PathVariable(value = "isbn") String isbn)  throws Exception { 
	    
	    System.out.println("Entering addPageToBook...");

	    //Find Existing Book by isbn
	    Book book= bookRepository.findByIsbn(isbn);
	    
	    //If book not found for isbn passed
	     if(null==book){
	         System.out.println("Book not found for isbn");
	         throw  new Exception("No Records found");
	     }
	     
	     System.out.println(book);
	     
	     //Add a New Page to the retrieved book 
	     book.getPages().add(new Page(555, "Let US C", "EndNote", book) );
	     
	     System.out.println("Adding new page to book...");
	     System.out.println(book);
	     
	     
	     //Save the updated book with new page
	     bookRepository.save(book);
	     
         System.out.println("Added new page to book...");
         System.out.println("Leaving addPageToBook");
         
	}
	


@GetMapping("/updateParent/{isbn}")
public void updateParent(@PathVariable(value = "isbn") String isbn) throws Exception { 

    System.out.println("Entering updateParent...");
   
    //Find Existing Book by isbn
    Book book= bookRepository.findByIsbn(isbn);

    //If book not found for isbn passed
     if(null==book){
         System.out.println("Book not found for isbn");
         throw  new Exception("No Records found");
     }
	     
	   System.out.println("Book object found");
	   System.out.println(book);

     
    System.out.println("Getting page collection from book object...");
    List<Page> pageList=book.getPages();
     
     
     System.out.println("Converting page collection List from book object TO MAP...");
     Map<String,Page> pageMap=listToMap(pageList);
     
     
     System.out.println("Deleting the 1st Page Object,one with PageId as 2 ");
     // Delete 1st Page Object, PageId is 2
     long pageIdToDelete=2;
     Page deletedPageObj=pageMap.remove(pageIdToDelete+"");
     
     
     System.out.println("Updating the 2nd Page Object, one with PageId as 3 ");
     // Update 2nd Page Object
     long pageIdToUpdate=3;
     Page pageObjToUpdate=pageMap.get(pageIdToUpdate+"");
     //Update chapter and content fields
     pageObjToUpdate.setChapter("Chapter-Updated");
     pageObjToUpdate.setContent("Content-Updated");
     
     pageMap.put(pageIdToUpdate+"",pageObjToUpdate);
     
     
     System.out.println("Create 2 new Page Objects...");
     // Add 2 New Random Page Objects
     Page pagen1=new Page(111, "Introduction contents", "Introduction", book);
     Page pagen2=new Page(222, "Summary contents", "Ending", book);
     
     
     System.out.println("Converting Map to List...");
     // Convert map to List
     pageList=mapToList(pageMap);
     
     System.out.println("Adding New Page Object to List...");
     pageList.add(pagen1);
     pageList.add(pagen2);
     
     
     System.out.println("Clearing the Page Entity Collection in Book Entity");
     
     //Update page Child Collection in book
     book.getPages().clear();
     
     System.out.println("Associating the Updated Page Entity Collection to Book Entity");
     
     
     book.getPages().addAll( (ArrayList<Page>) pageList);
     
     //for (Page page : pageList) {
            //pageList.add(pagen1);
            //pageList.add(pagen2);
      //}

    //book.getPages().add(pagen1);
    //book.getPages().add(pagen2);
     
     
     System.out.println("Saving the Book with updated pages...");
     //Save the book
     Book bookUpd=bookRepository.save(book);
     System.out.println("Updated book with pages...");
     System.out.println(bookUpd);
     System.out.println("Leaving updateParent.");
}
	
	@GetMapping("/updateBookPageById/{isbn}/{pageId}")
	public void updateBookPageById(@PathVariable(value = "isbn") String isbn,@PathVariable(value = "pageId") long pageId,@RequestParam(value = "content") String content) throws Exception { 
	    
	 	System.out.println("Entering updateBookPageById...");
	 	System.out.println("Received isbn:" + isbn);
	 	System.out.println("Received pageId:" + pageId);
	 	System.out.println("Received content:" + content);

	    //Find Existing Book by isbn
	    Book book= bookRepository.findByIsbn(isbn);
	    
	    //If book not found for isbn passed
	     if(null==book){
	         System.out.println("Book not found for isbn");
	         throw  new Exception("No Records found");
	     }
	     
	     
	    System.out.println(book);
	    
	    Page pageToChange=new Page();
	    
	    //Fetch list of pages in Book 
        List<Page> pageList=book.getPages();

       System.out.println("Got Page list from book object") ;
        //Convert list of pages to map keyed by isbn
        Map<String,Page> pageMap=listToMap(pageList);
       
       System.out.println("Converted pageList to pageMap") ; 
       
       
        //fEtch the page object for pased pageId
        pageToChange=pageMap.get(pageId +"");
        
       System.out.println("Got the page object to update..") ;  
       
       
        //Make Changes to page object
        pageToChange.setContent(content);
       
       System.out.println("After updating changes to page object");
        
        //Set the page object to map with key as pageId
       pageMap.put(pageId + "",pageToChange);
        
       System.out.println("After added updated page object back to map");
        
        
        long l=3;
        Page page=pageMap.remove(l+"");
        if(null==page){
          System.out.println("Key not found...");
        }else{
         System.out.println("Removed...");
         
         System.out.println(page);
        }
        System.out.println("Convert page map to page list...");
        
        //Convert map to List
        pageList=mapToList(pageMap);
        
        System.out.println("Updated List");
        System.out.println(pageList);
        
        
        //Set the updated Page list to book
        book.getPages().clear();
        book.getPages().addAll( (ArrayList<Page>) pageList);
        
        
        
        System.out.println("Update book with pages");
        System.out.println(book);
        
        System.out.println("Updating book...");
       //Save the updated book with new page
 	     bookRepository.save(book);
         System.out.println("Leaving updateBookPageById");
         
   
	}
	
	/*@GetMapping("/book/{id}")
	public void getBookByIsbn(@PathVariable(value = "id") long id) throws Exception { 
	     System.out.println("Received id:" + id);
	     Book book= bookRepository.findById(id);
	     
	     if(null==book){
	         throw  new Exception("No Records found");
	     }
         
         System.out.println(book);
	}*/
	
}