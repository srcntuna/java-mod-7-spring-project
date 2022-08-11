package com.example.BookListApp.service;

import com.example.BookListApp.dto.BookDTO;
import com.example.BookListApp.dto.BookResponseDTO;
import com.example.BookListApp.dto.CreateBookDTO;
import com.example.BookListApp.dto.UpdateBookDTO;
import com.example.BookListApp.exception.NotFoundException;
import com.example.BookListApp.model.Book;
import com.example.BookListApp.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper mapper;

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(activity -> mapper.map(activity, BookDTO.class))
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
        Book book = mapper.map(createBookDTO, Book.class);
        return mapper.map(bookRepository.save(book), BookDTO.class);
    }

    public BookDTO updateBookById(Integer id, UpdateBookDTO newBookDTO){
        Book book = bookRepository.findById(id).orElseThrow(()->new NotFoundException("Book not found"));

        Book newBook = mapper.map(newBookDTO,Book.class);

        return mapper.map(bookRepository.save(newBook), BookDTO.class);

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

        return bookRepository.findBooksbyBooks_genre_id(genreId).stream().map(book -> mapper.map(book,BookDTO.class)).toList();

    }



}
