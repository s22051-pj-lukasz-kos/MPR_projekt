package com.pjatk.kos.springboot.booklist.service;

import com.pjatk.kos.springboot.booklist.entity.Book;

import java.util.List;

// lista metod do zaimplementowania w warstwie serwisowej
public interface BookListService {
    List<Book> findAll();

    Book findById(Integer id);

    void save(Book book);

    void deleteById(Integer id);

    void replace(Book book);
}
