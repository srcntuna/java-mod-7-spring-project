package com.example.BookListApp.repository;

import com.example.BookListApp.dto.BookResponseDTO;
import com.example.BookListApp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    public Optional<Book> findByTitle(String title);
}
