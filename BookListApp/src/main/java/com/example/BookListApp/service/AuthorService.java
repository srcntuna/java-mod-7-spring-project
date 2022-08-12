package com.example.BookListApp.service;

import com.example.BookListApp.dto.BookDTO;
import com.example.BookListApp.model.Author;
import com.example.BookListApp.repository.AuthorRepository;
import com.example.BookListApp.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper mapper;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthor(String name){

        Author author = authorRepository.findByName(name);

        if(author == null){
            return Author.builder().name(name).build();
        }

        return author;

    }
}
