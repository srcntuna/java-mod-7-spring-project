package com.example.BookListApp.service;

import com.example.BookListApp.dto.CreateReadingListDTO;
import com.example.BookListApp.dto.ReadingListDTO;
import com.example.BookListApp.dto.UserResponseDTO;
import com.example.BookListApp.model.ReadingList;
import com.example.BookListApp.model.User;
import com.example.BookListApp.repository.BookRepository;
import com.example.BookListApp.repository.ReadingListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class ReadingListService {

    @Autowired
    private ReadingListRepository readingListRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    public List<ReadingListDTO> getReadingListByUserId(Integer user_id){

        List<ReadingList> readingLists = readingListRepository.findAllByUserId(user_id);

        return readingLists.stream()
                .map(readingList -> mapper.map(readingList,
                        ReadingListDTO.class))
                .toList();

    }

    public UserResponseDTO createReadingList( Integer user_id, CreateReadingListDTO createReadingListDTO) {
        ReadingList readingList = new ReadingList();

        User user = userService.findUserById(user_id);
        readingList.setName(createReadingListDTO.getName());
        readingList.setUser(user);

        readingList = readingListRepository.save(readingList);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user_id);
        userResponseDTO.setUsername(user.getUsername());
        List<ReadingList> userCurrentList = user.getReadingLists();
        userCurrentList.add(readingList);
        userResponseDTO.setReadingList(userCurrentList);

        return userResponseDTO;
    }

    public List<ReadingListDTO> getReadingListByUserIdAndReadingListId(Integer user_id,Integer list_id){

        return userService.findUserById(user_id).getReadingLists().stream().filter(readingList -> readingList.getId() == list_id).map(readingList -> mapper.map(readingList,ReadingListDTO.class)).toList();

    }



}
