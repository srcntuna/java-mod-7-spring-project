package com.example.BookListApp.model;

import com.example.BookListApp.dto.GenreDTO;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "books")
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "title is mandatory")

    private String title;

    @Min(10)
    private int pages;

    private LocalDateTime published;

    @ManyToMany(mappedBy = "books")
    private List<ReadingList> readingLists = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();


}
