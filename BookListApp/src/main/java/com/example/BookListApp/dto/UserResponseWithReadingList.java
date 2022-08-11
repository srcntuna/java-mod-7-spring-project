package com.example.BookListApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseWithReadingList {

    private int id;

    private String username;

    private List<ReadingListDTO> readingList;

}
