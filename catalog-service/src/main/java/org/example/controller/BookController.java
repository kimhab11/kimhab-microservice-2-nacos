package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.BookResponse;
import org.example.entity.BookEntity;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("book")
@Slf4j
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
        log.info("books: {}", books.size());

        List<BookResponse> bookResponseSet = new ArrayList<>();
        books.forEach(book -> bookResponseSet.add(new BookResponse(book)));
        log.info("bookResponseSet: {}",  bookResponseSet.size());

        return ResponseEntity.ok(bookResponseSet);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBookById(@PathVariable long id){
        var book = bookRepository.findById(id).orElseThrow(()->new RuntimeException("not found"));
        var bookRes = new BookResponse(book);
        log.info("book: {}", bookRes);

        return ResponseEntity.ok(bookRes);
    }

}
