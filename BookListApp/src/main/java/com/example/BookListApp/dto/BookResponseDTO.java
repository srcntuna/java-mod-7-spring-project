package com.example.BookListApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookResponseDTO {

    private int id;

    private String title;

    private String author_name;

    private List<GenreDTO> genreDTOList;

}
