package com.example.BookListApp.dto;

import com.example.BookListApp.model.Book;
import lombok.Data;

import java.util.List;

@Data

public class GenreResponseWithBooksDTO {

    private int id;

    private String name;

    private List<BookDTO> books;
}
