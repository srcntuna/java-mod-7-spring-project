package com.example.BookListApp.service;

import com.example.BookListApp.dto.CreateUserDTO;
import com.example.BookListApp.dto.UserDTO;
import com.example.BookListApp.dto.UserResponseDTO;
import com.example.BookListApp.exception.NotFoundException;
import com.example.BookListApp.model.Book;
import com.example.BookListApp.model.User;
import com.example.BookListApp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    public UserDTO create(CreateUserDTO createUserDTO) {
        User user = mapper.map(createUserDTO, User.class);
        user.setReadingLists(new ArrayList<>());
        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    public ResponseEntity<String> deleteById(Integer id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        }
        else {
            throw new NotFoundException("User not found");
        }
    }

    public User findUserById(Integer id){

        return userRepository.findById(id).orElseThrow(()->new NotFoundException("User not found"));

    }


    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(activity -> mapper.map(activity, UserDTO.class))
                .toList();
    }


}
