package com.pjatk.kos.springboot.booklist.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {

    // klucz główny o generowanej wartości
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    // relacja encji
    @OneToMany(cascade = CascadeType.ALL)
    private List<Author> authors;

    public Book() {
    }

    public Book(Integer id, String title, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
    }

    public Book(String title, List<Author> authors) {
        this.title = title;
        this.authors = authors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> author) {
        this.authors = author;
    }
}
