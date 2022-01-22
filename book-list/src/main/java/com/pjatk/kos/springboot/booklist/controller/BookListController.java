package com.pjatk.kos.springboot.booklist.controller;

import com.pjatk.kos.springboot.booklist.entity.Book;
import com.pjatk.kos.springboot.booklist.service.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookListController {

    // wstrzyknięcie zależności do serwisu
    // pole do interfejsu serwisu, a w konsekwencji do bazy danych
    private BookListService bookListService;

    // wstrzyknięcie zależności serwisu do kontrolera
    @Autowired
    public BookListController(BookListService bookListService) {
        this.bookListService = bookListService;
    }

    // wyświetl listę i pobiera dane z db
    @GetMapping("/list")
    public String listBooks(Model model) {

        // pobierz książki z db
        List<Book> books = bookListService.findAll();

        // dodaj do modelu
        model.addAttribute("books", books);

        // przekieruj do list.html
        return "books/list";
    }

    // wyświetl formularz do dodawania książek
    @GetMapping("/showForm")
    public String showForm(Model model) {

        // trzeba stworzyć nowy pusty obiekt Book, aby móc na nim działać w formularzu
        Book book = new Book();

        // i dodaj ten pusty obiekt do modelu
        model.addAttribute("book", book);

        // i przekieruj do add-book.html
        return "books/add-book";
    }

    // przekierowanie do formularza, gdzie dodamy książkę
    @GetMapping("/updateButton")
    public String updateButton(@RequestParam("bookId") Integer id,
                               Model model) {

        // znajdz książkę w db
        Book book = bookListService.findById(id);

        // ustaw książkę jako atrybut modelu
        model.addAttribute("book", book);

        // wyślij do formularza
        return "books/add-book";
    }

    // zapisz książkę, zczytaj książkę z modelu
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {

        // i zapisz ją do repo
        bookListService.save(book);

        // przekieruj do list.html
        return "redirect:/books/list";
    }

    // przekierowuje do formularza, gdzie dodajemy autora
    @GetMapping("/addAuthorButton")
    public String addAuthorButton(@RequestParam("bookId") Integer id,
                                  Model model) {

        // znajdz książkę w db
        Book book = bookListService.findById(id);

        // i dodaj ją do modelu
        model.addAttribute("book", book);

        // i przekieruj do add-author
        return "books/add-author";
    }

    // zapisuje autora, pobierz książkę z modelu
    @PostMapping("/saveAuthor")
    public String saveAuthor(@ModelAttribute("book") Book book) {

        // zamień książkę na tę z wpisem o autorze
        bookListService.replace(book);

        // przekieruj do list.html
        return "redirect:/books/list";
    }

    // usuń książkę
    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") Integer id) {

        // usuń wpis o książce
        bookListService.deleteById(id);

        // przekieruj do list.html
        return "redirect:/books/list";
    }
}