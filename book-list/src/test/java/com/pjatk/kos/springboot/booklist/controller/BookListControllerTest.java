package com.pjatk.kos.springboot.booklist.controller;

import com.pjatk.kos.springboot.booklist.entity.Book;
import com.pjatk.kos.springboot.booklist.service.BookListServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookListControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookListServiceImpl bookListService;

    @Test
    void listBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/list"))
                .andExpect(model().attributeExists("books"));
    }

    @Test
    void showForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/showForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/add-book"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    void updateButton() throws Exception {
        Book book = bookListService.findById(1);
        mockMvc.perform(MockMvcRequestBuilders.get("/books/updateButton")
                    .requestAttr("bookId", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("books/add-book"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    void saveBook() {

    }

    @Test
    void addAuthorButton() {
    }

    @Test
    void saveAuthor() {
    }

    @Test
    void delete() {
    }
}