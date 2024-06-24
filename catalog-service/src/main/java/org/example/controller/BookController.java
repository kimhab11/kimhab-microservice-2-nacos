package org.example.controller;

import org.example.entity.BookEntity;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("insert")
    public ResponseEntity<?> addBook(@RequestBody BookEntity book){
        bookRepository.save(book);
        return new ResponseEntity<>(book,HttpStatus.CREATED);
    }

    @GetMapping("list-all")
    public ResponseEntity<?> listAll(){
        var books = bookRepository.findAll();
        return ResponseEntity.ok(books);
    }
}
