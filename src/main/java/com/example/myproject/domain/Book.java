package com.example.myproject.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String author;
    @Column(unique = true)
    private String isbn;

    @OneToMany( 
        mappedBy = "book",
        fetch = FetchType.EAGER,
        cascade = { CascadeType.ALL  },
        orphanRemoval = true) 
    private List<Page> pages;

    public Book() {
    }

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Page> pages) {
        if (this.pages == null) {
				this.pages = new ArrayList<Page>();
		}
        this.pages.addAll(pages);
    }

    public void addPage(Page page){
        
        if (page != null) {
			if (pages == null) {
				pages = new ArrayList<Page>();
			}
			page.setBook(this);
			pages.add(page);
		}
    }
    
    public void removePage(Page page){
        pages.remove(page);
        page.setBook(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id==book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}