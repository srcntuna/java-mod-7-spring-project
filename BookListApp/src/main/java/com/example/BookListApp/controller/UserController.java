package com.example.BookListApp.controller;

import com.example.BookListApp.dto.CreateUserDTO;
import com.example.BookListApp.dto.UserDTO;
import com.example.BookListApp.model.User;
import com.example.BookListApp.service.BookService;
import com.example.BookListApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public UserDTO create(CreateUserDTO createUserDTO){
        return userService.create(createUserDTO);
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable Integer id){
        userService.deleteById(id);
    }
}