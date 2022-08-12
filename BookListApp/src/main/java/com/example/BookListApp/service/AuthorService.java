package com.example.BookListApp.service;

import com.example.BookListApp.dto.AuthorDTO;
import com.example.BookListApp.dto.BookDTO;
import com.example.BookListApp.model.Author;
import com.example.BookListApp.repository.AuthorRepository;
import com.example.BookListApp.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper mapper;

    public List<AuthorDTO> getAllAuthors() {

        return authorRepository.findAll()
                .stream()
                .map(author -> mapper.map(author, AuthorDTO.class))
                .toList();
    }

    public Author getAuthor(String name){

        Author author = authorRepository.findByName(name.toLowerCase());

        if(author == null){
            return Author.builder().name(name).bookList(new ArrayList<>()).build();
        }

        return author;

    }
}
