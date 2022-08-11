package com.example.BookListApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReadingListResponseDTO {

    private int id;

    private int user_id;

    private String name;

    private List<BookDTO> books;


}
