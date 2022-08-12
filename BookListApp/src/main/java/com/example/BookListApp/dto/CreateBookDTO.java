package com.example.BookListApp.dto;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class CreateBookDTO {

private String title;

private int pages;

@NotNull
private String author_name;

@Temporal(TemporalType.DATE)
private Date published;

private List<Integer> genreIds;

}
