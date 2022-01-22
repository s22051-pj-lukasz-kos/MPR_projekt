package com.pjatk.kos.springboot.booklist.dao;

import com.pjatk.kos.springboot.booklist.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

// repozytorium bazy danych
public interface BookRepository extends JpaRepository<Book, Integer> {
}
