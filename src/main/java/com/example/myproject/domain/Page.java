package com.example.myproject.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "pages")
public class Page implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int number;
    private String content;
    private String chapter;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Page() {
    }

    public Page(int number, String content, String chapter, Book book) {
        this.number = number;
        this.content = content;
        this.chapter = chapter;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return id==page.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", number=" + number +
                ", content='" + content + '\'' +
                ", chapter='" + chapter + '\'' +
                ", book=" + book +
                '}';
    }
}