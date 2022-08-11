package com.example.BookListApp.dto;

import com.example.BookListApp.model.ReadingList;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {

    private int id;

    private String username;

    private List<ReadingList> readingList;
}
