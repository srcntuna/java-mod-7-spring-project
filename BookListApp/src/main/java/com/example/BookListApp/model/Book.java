package com.example.BookListApp.model;

import com.example.BookListApp.dto.GenreDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "title is mandatory")

    private String title;

    @Min(10)
    private int pages;

    private LocalDateTime published;

    @ManyToMany(mappedBy = "reading_lists")
    private List<ReadingList> readingLists = new ArrayList<>();

    @NotEmpty(message = "author_name is mandatory")
    @ManyToOne
    private Author author;

    @Size(min = 1, message = "it must have min 1 genre")
    @ManyToMany
    @JoinTable(name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();


}
