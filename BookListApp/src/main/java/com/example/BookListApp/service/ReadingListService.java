package com.example.BookListApp.service;

import com.example.BookListApp.dto.*;
import com.example.BookListApp.exception.NotFoundException;
import com.example.BookListApp.model.Book;
import com.example.BookListApp.model.ReadingList;
import com.example.BookListApp.model.User;
import com.example.BookListApp.repository.BookRepository;
import com.example.BookListApp.repository.ReadingListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class ReadingListService {

    @Autowired
    private ReadingListRepository readingListRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper mapper;

    public List<ReadingListDTO> getReadingListByUserId(Integer user_id){

        List<ReadingList> readingLists = readingListRepository.findAllByUserId(user_id);

        return readingLists.stream()
                .map(readingList -> mapper.map(readingList,
                        ReadingListDTO.class))
                .toList();

    }

    public UserResponseDTO createReadingList(@Valid @RequestBody Integer user_id, CreateReadingListDTO createReadingListDTO) {

        System.out.println("Line 45 "+user_id);
        System.out.println("Line 46 "+createReadingListDTO.getName());

        ReadingList readingList = ReadingList.builder().name(createReadingListDTO.getName()).books(new ArrayList<>()).build();

        List<Integer> bookIds  = createReadingListDTO.getBookIds();

        ReadingList finalReadingList = readingList;
        bookIds.forEach(bookId ->{
            Book book = bookRepository.findById(bookId).orElseThrow(()->new NotFoundException("Book with id "+bookId+ " not found"));

            finalReadingList.addBook(book);

        });

        User user = userService.findUserById(user_id);
        readingList.setUser(user);


        readingList = readingListRepository.save(readingList);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user_id);
        userResponseDTO.setUsername(user.getUsername());
        List<ReadingList> userCurrentList = user.getReadingLists();
        userResponseDTO.setReadingList(userCurrentList.stream().map(currList -> mapper.map(currList,ReadingListDTO.class)).collect(Collectors.toList()));

        return userResponseDTO;
    }

    public ReadingListResponseDTO getReadingListByUserIdAndReadingListId(Integer user_id,Integer list_id){

        ReadingListResponseDTO readingListResponseDTO =  userService.findUserById(user_id).getReadingLists().stream().filter(readingList -> readingList.getId() == list_id).map(readingList -> mapper.map(readingList,ReadingListResponseDTO.class)).findFirst().orElseThrow(() ->new NotFoundException("Reading list not found with given Ids"));

        readingListResponseDTO.setUser_id(user_id);

        return readingListResponseDTO;

    }



}
