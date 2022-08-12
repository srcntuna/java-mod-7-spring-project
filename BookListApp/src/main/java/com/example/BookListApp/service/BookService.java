package com.example.BookListApp.service;

import com.example.BookListApp.dto.BookDTO;
import com.example.BookListApp.dto.BookResponseDTO;
import com.example.BookListApp.dto.CreateBookDTO;
import com.example.BookListApp.dto.UpdateBookDTO;
import com.example.BookListApp.exception.NotFoundException;
import com.example.BookListApp.model.Author;
import com.example.BookListApp.model.Book;
import com.example.BookListApp.model.Genre;
import com.example.BookListApp.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreService genreService;

    @Autowired
    private  AuthorService authorService;

    @Autowired
    private ModelMapper mapper;

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> mapper.map(book, BookDTO.class))
                .toList();
    }

    public BookResponseDTO findBookById(Integer id) {

        Book book = bookRepository.findById(id).orElseThrow(()->new NotFoundException("Book not found"));

        String author_Name = book.getAuthor().getName();

        BookResponseDTO bookDTO = mapper.map(book,BookResponseDTO.class);

        bookDTO.setAuthor_name(author_Name);

        return  bookDTO;

    }

    public BookDTO createBook(CreateBookDTO createBookDTO) {
        System.out.println("hey here!!");
        String authorsName = createBookDTO.getAuthor_name();
        List<Integer> genreIds = createBookDTO.getGenreIds();

        Author author = authorService.getAuthor(authorsName);

        Book book = mapper.map(createBookDTO, Book.class);

        book.setAuthor(author);
        List<Genre> genreList = new ArrayList<>();
        genreIds.forEach(id ->{
            Genre genre = genreService.getGenreById(id);
            genreList.add(genre);
        });
        book.setGenres(genreList);
        return mapper.map(bookRepository.save(book), BookDTO.class);
    }

    public BookDTO updateBookById(Integer id, UpdateBookDTO newBookDTO){
        Book book = bookRepository.findById(id).orElseThrow(()->new NotFoundException("Book not found"));

        book.setTitle(newBookDTO.getTitle());
        book.setPages(newBookDTO.getPages());

        return mapper.map(bookRepository.save(book), BookDTO.class);

    }

    public void deleteById(Integer id) {
        if(bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Book not found");
        }
    }


    public List<BookDTO> getAllBooksByGenreId(Integer genreId) {

        Genre genre = genreService.getGenreById(genreId);

        List<Book> books = genre.getBooks();

        return books.stream().map(book -> mapper.map(book,BookDTO.class)).toList();

    }



}
