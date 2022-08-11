package com.example.BookListApp.repository;

import com.example.BookListApp.model.ReadingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingListRepository extends JpaRepository<ReadingList,Integer> {

    List<ReadingList> findAllByUserId(Integer userId);


}
