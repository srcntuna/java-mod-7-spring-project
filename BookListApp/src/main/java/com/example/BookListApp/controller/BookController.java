package com.example.BookListApp.controller;

import com.example.BookListApp.dto.BookDTO;
import com.example.BookListApp.dto.BookResponseDTO;
import com.example.BookListApp.dto.CreateBookDTO;
import com.example.BookListApp.dto.UpdateBookDTO;
import com.example.BookListApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")

public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<BookDTO> getAllBooks() {

            return bookService.getAllBooks();


    }

    @GetMapping("/books/{id}")
    public BookResponseDTO findBookById( @PathVariable  Integer id) {
        return bookService.findBookById(id);
    }

    @PostMapping("/books")
    public BookDTO createBook(@Valid @RequestBody CreateBookDTO createBookDTO) {
        return bookService.createBook(createBookDTO);
    }

    @PutMapping("/books/{id}")
    public BookDTO updateBookById(@PathVariable Integer id, @Valid @RequestBody UpdateBookDTO updateBookDTO){
        return bookService.updateBookById(id, updateBookDTO);
    }

    @DeleteMapping("/books/{id}")
    public void deleteById(@PathVariable Integer id) {
        bookService.deleteById(id);
    }


    @GetMapping("genre/{id}/books")
    public List<BookDTO> getAllBooksByGenreId(@PathVariable Integer id){
        return bookService.getAllBooksByGenreId(id);
    }

    @GetMapping(value = "/books/title/{title}")
    public BookResponseDTO findBookByTitle(@PathVariable String title) {
        return bookService.findBookByTitle(title);
    }

}
