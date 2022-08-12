package com.example.BookListApp;

import com.example.BookListApp.model.Author;
import com.example.BookListApp.model.Book;
import com.example.BookListApp.model.Genre;
import com.example.BookListApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class BookListAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookListAppApplication.class, args);
	}

	@Component
	public class StartUpRunner implements CommandLineRunner {

		@Autowired
		private BookRepository bookRepository;
		@Override
		public void run(String... args) throws Exception {
			System.out.println("Running");




		}
	}

}
