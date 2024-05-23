package org.codewithcaleb.spring_batch_001.controller;

import org.codewithcaleb.spring_batch_001.entity.BookEntity;
import org.codewithcaleb.spring_batch_001.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {


    @Autowired
    private BookRepository bookRepository;


    @GetMapping
    public List<BookEntity> getAll(){
        return bookRepository.findAll();
    }
}
