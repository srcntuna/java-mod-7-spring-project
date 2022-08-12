package com.example.BookListApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateReadingListDTO {

    private String name;

    private List<Integer> bookIds;

}

