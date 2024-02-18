package com.Library.Msystem.controller;

import com.Library.Msystem.model.Books;
import com.Library.Msystem.repository.BookRepository;
import com.Library.Msystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> books = bookService.findAllBooks();
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable("id") Long id) {
        Books book = bookService.findBookById(id);
        if (book != null) {
            return ResponseEntity.ok().body(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/{id}/buy")
    public ResponseEntity<String> buyBook(@PathVariable("id") Long id) {
        bookService.buyBook(id);
        return ResponseEntity.ok().body("Book purchased successfully!");
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<String> cancelBook(@PathVariable("id") Long id) {
        bookService.cancelBook(id);
        return ResponseEntity.ok().body("Book canceled successfully!");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Books>> searchBooks(@RequestParam("q") String query) {
        List<Books> books = bookService.searchBooks(query);
        return ResponseEntity.ok().body(books);
    }
}

