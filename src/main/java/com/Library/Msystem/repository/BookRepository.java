package com.Library.Msystem.repository;

import com.Library.Msystem.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {
    List<Books> findByBookNameContainingIgnoreCase(String bookName);
}
