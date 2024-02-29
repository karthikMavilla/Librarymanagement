package com.Library.Msystem.service;

import com.Library.Msystem.model.Books;
import com.Library.Msystem.model.User;
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
            book.setPurchasedTime(LocalDateTime.now());
            book.setPurchased(true);
            bookRepository.save(book);
        });
    }

    public void cancelBook(Long bookId) {
        Optional<Books> optionalBook = bookRepository.findById(bookId);
        optionalBook.ifPresent(book -> {
            book.setPurchased(false);
            book.setCanceledTime(LocalDateTime.now());
            bookRepository.save(book);
        });
    }

    public List<Books> searchBooks(String query) {
        return bookRepository.findByBookNameContainingIgnoreCase(query);
    }

    public List<Books> findAvailableBooksByLocation(String location) {
        return bookRepository.findByLocation(location);
    }

    public List<Books> findAvailableBooksByLocationAndNotPurchased(String location) {
        return bookRepository.findAvailableBooksByLocationAndPurchasedFalse(location);
    }

    public List<Books> findBooksByUser(User user) {
        return bookRepository.findByUser(user);
    }
}