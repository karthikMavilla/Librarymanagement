package com.Library.Msystem.service;

import com.Library.Msystem.model.Books;
import com.Library.Msystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Books> findAllBooks() {
        return bookRepository.findAll();
    }

    public Books findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void buyBook(Long bookId) {
        Optional<Books> optionalBook = bookRepository.findById(bookId);
        optionalBook.ifPresent(book -> {
            // Perform logic related to buying the book, e.g., updating status or marking as purchased
            book.setPurchased(true);
            bookRepository.save(book);
        });
    }

    public void cancelBook(Long bookId) {
        Optional<Books> optionalBook = bookRepository.findById(bookId);
        optionalBook.ifPresent(book -> {
            // Perform logic related to canceling the book, e.g., updating status or marking as canceled
            book.setPurchased(false);
            bookRepository.save(book);
        });
    }

    public List<Books> searchBooks(String query) {
        // Implement search functionality based on your requirements
        // For example, search by book title or author
        return bookRepository.findByBookNameContainingIgnoreCase(query);
    }
}