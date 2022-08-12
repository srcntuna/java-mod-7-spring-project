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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        bookDTO.setAuthor_name(author_Name.toLowerCase());

        return  bookDTO;

    }

    public BookDTO createBook(CreateBookDTO createBookDTO) {
        System.out.println("hey here!!");
        String authorsName = createBookDTO.getAuthor_name();
        createBookDTO.setTitle(createBookDTO.getTitle().toLowerCase());

        List<Integer> genreIds = createBookDTO.getGenreIds();

        Author author = authorService.getAuthor(authorsName.toLowerCase());

        Book book = mapper.map(createBookDTO, Book.class);

        book.setAuthor(author);
        author.getBookList().add(book);
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

        book.setTitle(newBookDTO.getTitle().toLowerCase());
        book.setPages(newBookDTO.getPages());

        return mapper.map(bookRepository.save(book), BookDTO.class);

    }

    public ResponseEntity<String> deleteById(Integer id) {
        if(bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);

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

    public BookResponseDTO findBookByTitle(String title) {

        System.out.println("this is title :" + title);



        Book book = bookRepository.findByTitle(title.toLowerCase()).orElseThrow(()->new NotFoundException("Book not found with title "+title));

        String author_Name = book.getAuthor().getName();

        BookResponseDTO bookDTO = mapper.map(book,BookResponseDTO.class);

        bookDTO.setAuthor_name(author_Name);

        return  bookDTO;

    }


}
