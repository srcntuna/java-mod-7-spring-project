package com.example.BookListApp.controller;

import com.example.BookListApp.dto.CreateUserDTO;
import com.example.BookListApp.dto.UserDTO;
import com.example.BookListApp.model.User;
import com.example.BookListApp.service.BookService;
import com.example.BookListApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public UserDTO createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        return userService.create(createUserDTO);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
      return userService.deleteById(id);
    }


    @GetMapping("/users")
    public List<UserDTO> getAll(){
        return userService.getAll();
    }


}
