package com.example.BookListApp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data

public class CreateGenreDTO {

    @NotNull
    private String name;
}
