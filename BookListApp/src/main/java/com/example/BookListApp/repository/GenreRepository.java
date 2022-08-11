package com.example.BookListApp.repository;

import com.example.BookListApp.model.Book;
import com.example.BookListApp.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Integer> {



}
