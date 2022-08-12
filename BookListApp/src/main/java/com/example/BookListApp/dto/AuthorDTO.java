package com.example.BookListApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorDTO {

    private int id;

    private String name;

    private List<BookDTO> bookList;

}
