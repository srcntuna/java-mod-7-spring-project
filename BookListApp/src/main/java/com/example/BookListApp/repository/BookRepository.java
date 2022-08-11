package com.example.BookListApp.repository;

import com.example.BookListApp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query("SELECT books.name FROM books INNER JOIN book_genres ON books.id = book_genres.book_id WHERE book_genres.genre_id =:inputGenreId")
    List<Book> findBooksbyBooks_genre_id(Integer inputGenreId);

}
