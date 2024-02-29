package com.Library.Msystem.controller;

import com.Library.Msystem.Auth.JwtService;
import com.Library.Msystem.model.Books;
import com.Library.Msystem.model.User;
import com.Library.Msystem.repository.UserRepository;
import com.Library.Msystem.service.BookService;
import com.Library.Msystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;



    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> books = bookService.findAllBooks();
        return ResponseEntity.ok().body(books);
    }
    @GetMapping("/available")
    @ResponseBody
    public List<Books> getUserInfo(@RequestHeader("Authorization") String token) {
        String username = jwtService.extarctUsername(token.substring(7));
        User user = userRepository.findByEmail(username).orElse(null);
        String location = (user != null) ? user.getLocation() : "Unknown";

        List<Books> availableBooks = bookService.findAvailableBooksByLocationAndNotPurchased(location);
        List<Books> userBooks = bookService.findBooksByUser(user);

        availableBooks.removeIf(book -> userBooks.contains(book) || (book.getUser() != null && book.getUser().getLocation().equals(location)));
        return availableBooks;
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

