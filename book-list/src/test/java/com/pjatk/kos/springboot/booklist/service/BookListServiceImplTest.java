package com.pjatk.kos.springboot.booklist.service;

import com.pjatk.kos.springboot.booklist.dao.BookRepository;
import com.pjatk.kos.springboot.booklist.entity.Author;
import com.pjatk.kos.springboot.booklist.entity.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class BookListServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookListServiceImpl bookListService;



    @Test
    void findAll() {

        // given

        // when
        bookListService.findAll();

        // then
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        // given
        Book book = new Book();
        Integer index = anyInt();
        // when
        when(bookRepository.findById(index)).thenReturn(Optional.of(book));
        Book repoBook = bookListService.findById(index);
        // then
        Assertions.assertThat(repoBook).isNotNull();
        verify(bookRepository, times(1)).findById(index);
    }

    @Test
    void save() {
        // given
        Book book = any(Book.class);
        // when
        bookListService.save(book);
        // then
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void deleteById() {
        // given
        Integer index = anyInt();
        // when
        bookListService.deleteById(index);

        // then
        verify(bookRepository, times(1)).deleteById(index);
    }


    @Test
    void replaceReturnRepoBook() {
        // given
        Book book = new Book();
        // when
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        Book repoBook = bookListService.findById(book.getId());
        // then
        Assertions.assertThat(repoBook).isNotNull();
        verify(bookRepository, times(1)).findById(book.getId());
    }

    @Test
    void replaceBookWithAddAuthor() {
        // given
        Author author = new Author("Andrzej", "Sapkowski");
        List<Author> authors = new ArrayList<Author>();
        authors.add(author);
        Book book = new Book("Narrenturm", authors);
        // when
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        bookListService.replace(book);
        // then
        verify(bookRepository, times(1)).findById(book.getId());
        verify(bookRepository,times(1)).save(book);
    }
}