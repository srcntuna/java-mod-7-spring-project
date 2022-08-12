package com.example.BookListApp.controller;

import com.example.BookListApp.dto.AuthorDTO;
import com.example.BookListApp.dto.BookDTO;
import com.example.BookListApp.model.Author;
import com.example.BookListApp.service.AuthorService;
import com.example.BookListApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public List<AuthorDTO> getAllBooks() {
        return authorService.getAllAuthors();
    }

}
