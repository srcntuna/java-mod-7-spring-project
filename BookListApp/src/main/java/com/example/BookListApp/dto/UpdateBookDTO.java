package com.example.BookListApp.dto;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class UpdateBookDTO {

    private String title;

    private int pages;

    private String author_name;

    @Temporal(TemporalType.DATE)
    private Date published;


}
