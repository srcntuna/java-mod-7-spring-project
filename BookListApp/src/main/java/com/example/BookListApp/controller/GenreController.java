package com.example.BookListApp.controller;

import com.example.BookListApp.dto.CreateGenreDTO;
import com.example.BookListApp.dto.GenreDTO;
import com.example.BookListApp.dto.GenreResponseDTO;
import com.example.BookListApp.dto.GenreResponseWithBooksDTO;
import com.example.BookListApp.model.Author;
import com.example.BookListApp.model.Genre;
import com.example.BookListApp.service.AuthorService;
import com.example.BookListApp.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/genres")
    public List<GenreResponseWithBooksDTO> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PostMapping("/genres")
    public GenreResponseDTO createGenre(@Valid @RequestBody CreateGenreDTO createGenreDTO){
        return genreService.createGenre(createGenreDTO);
    }


}
