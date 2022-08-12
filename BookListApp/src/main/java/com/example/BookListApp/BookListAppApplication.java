package com.example.BookListApp;

import com.example.BookListApp.dto.CreateBookDTO;
import com.example.BookListApp.dto.CreateReadingListDTO;
import com.example.BookListApp.model.*;
import com.example.BookListApp.repository.BookRepository;
import com.example.BookListApp.repository.GenreRepository;
import com.example.BookListApp.repository.UserRepository;
import com.example.BookListApp.service.BookService;
import com.example.BookListApp.service.ReadingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BookListAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookListAppApplication.class, args);
	}

	@Component
	public class StartUpRunner implements CommandLineRunner {

		@Autowired
		private UserRepository userRepository;

		@Autowired
		private BookService bookService;

		@Autowired
		private ReadingListService readingListService;

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

			User sercan = User.builder().username("Sercan").password("123456").build();

			userRepository.save(sercan);

			CreateBookDTO createBookDTO = new CreateBookDTO();
			createBookDTO.setTitle("Hamlet");
			createBookDTO.setPages(1000);
			createBookDTO.setAuthor_name("Shakespeare");
			createBookDTO.setPublished(new Date());

			List<Integer> genreIds = new ArrayList<>();
			genreIds.add(1);
			genreIds.add(2);

			createBookDTO.setGenreIds(genreIds);

           bookService.createBook(createBookDTO);

			CreateReadingListDTO createReadingListDTO = new CreateReadingListDTO();
			createReadingListDTO.setName("Winter Reading List");
			List<Integer> bookIds = new ArrayList<>();
			bookIds.add(1);
			createReadingListDTO.setBookIds(bookIds);

			readingListService.createReadingList(sercan.getId(),createReadingListDTO);



		}
	}

}
