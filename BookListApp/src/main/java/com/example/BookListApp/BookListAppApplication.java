package com.example.BookListApp;

import com.example.BookListApp.model.Author;
import com.example.BookListApp.model.Book;
import com.example.BookListApp.model.Genre;
import com.example.BookListApp.repository.BookRepository;
import com.example.BookListApp.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BookListAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookListAppApplication.class, args);
	}

	@Component
	public class StartUpRunner implements CommandLineRunner {

		@Autowired
		private GenreRepository genreRepository;
		@Override
		public void run(String... args) throws Exception {
			System.out.println("Running");


			Genre thriller = Genre.builder().name("thriller").build();
			Genre horror = Genre.builder().name("horror").build();
			Genre historical = Genre.builder().name("historical").build();
			Genre romance = Genre.builder().name("romance").build();
			Genre mystery = Genre.builder().name("mystery").build();

			List<Genre> genreList = new ArrayList<>(List.of(thriller,historical,horror,mystery,romance));

			genreList.forEach(genre -> genreRepository.save(genre) );



		}
	}

}
