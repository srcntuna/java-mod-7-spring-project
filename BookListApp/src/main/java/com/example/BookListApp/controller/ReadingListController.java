package com.example.BookListApp.controller;

import com.example.BookListApp.dto.CreateReadingListDTO;
import com.example.BookListApp.dto.ReadingListDTO;
import com.example.BookListApp.dto.ReadingListResponseDTO;
import com.example.BookListApp.dto.UserResponseDTO;
import com.example.BookListApp.service.BookService;
import com.example.BookListApp.service.ReadingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ReadingListController {

    @Autowired
    private ReadingListService readingListService;

    @GetMapping("/users/{id}/reading_lists")
    public List<ReadingListDTO> getReadingListByUserId(@PathVariable Integer id){
        return readingListService.getReadingListByUserId(id);
    }

    @PostMapping("/users/{id}/reading_lists")
    public UserResponseDTO createReadingList(@PathVariable Integer id, @Valid @RequestBody  CreateReadingListDTO createReadingListDTO){

        return readingListService.createReadingList(id,createReadingListDTO);
    }

    @GetMapping("/users/{id}/reading_lists/{list_id}")
    public ReadingListResponseDTO getReadingListByUserIdAndReadingListId(@PathVariable Integer id, @PathVariable Integer list_id){
        return readingListService.getReadingListByUserIdAndReadingListId(id,list_id);
    }



}
