package com.Library.Msystem.repository;

import com.Library.Msystem.model.Books;
import com.Library.Msystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {
    List<Books> findByBookNameContainingIgnoreCase(String bookName);
    List<Books> findByLocation(String location);

    List<Books> findAvailableBooksByLocationAndPurchasedFalse(String location);

    List<Books> findByUser(User user);
}
