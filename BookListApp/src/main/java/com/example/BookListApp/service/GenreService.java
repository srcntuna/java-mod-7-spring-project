package com.example.BookListApp.service;

import com.example.BookListApp.dto.BookDTO;
import com.example.BookListApp.dto.CreateGenreDTO;
import com.example.BookListApp.dto.GenreDTO;
import com.example.BookListApp.dto.UserDTO;
import com.example.BookListApp.exception.NotFoundException;
import com.example.BookListApp.model.Author;
import com.example.BookListApp.model.Genre;
import com.example.BookListApp.model.User;
import com.example.BookListApp.repository.AuthorRepository;
import com.example.BookListApp.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper mapper;

    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll()
                .stream()
                .map(book -> mapper.map(book, GenreDTO.class))
                .toList();
    }

    public Genre getGenreById(Integer id){
        Genre genre = genreRepository.findById(id).orElseThrow(()->new NotFoundException("Genre not found"));

        return  genre;
    }

    public GenreDTO createGenre(CreateGenreDTO createGenreDTO){

        Genre genre = mapper.map(createGenreDTO, Genre.class);
        genre.setBooks(new ArrayList<>());

        return mapper.map(genreRepository.save(genre), GenreDTO.class);



    }

}
