package com.pjatk.kos.springboot.booklist.service;

import com.pjatk.kos.springboot.booklist.dao.BookRepository;
import com.pjatk.kos.springboot.booklist.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Controller that create service layer
@Service
public class BookListServiceImpl implements BookListService {

    // repozytorium (db)
    private BookRepository bookRepository;

    // constructor injection, wstrzykujemy repozytorium do serwisu
    @Autowired
    public BookListServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // zwraca listę książek z db
    @Override
    public List<Book> findAll() {

        return bookRepository.findAll();
    }

    // zwróć książkę z db po id
    @Override
    public Book findById(Integer id) {
        Optional<Book> result = bookRepository.findById(id);

        Book book = null;
        if (result.isPresent()) {
            book = result.get();
        } else {
            throw new RuntimeException("Nie znaleziono książki o id " + id);
        }

        return book;
    }

    // zapisz książkę do db
    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    // usuń z db po id
    @Override
    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    // zamień książkę z db na zaktualizowaną wersję
    public void replace(Book book) {
        // znajdż książkę z db po id
        Book repoBook = findById(book.getId());

        // muszę pobrać z book pierwszy wpis z listy autorów i dodać go do repoBook.authors
        repoBook.getAuthors().add(book.getAuthors().get(0));

        // zapis książki do db
        bookRepository.save(repoBook);
    }
}
