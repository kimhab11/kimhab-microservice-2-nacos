package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.AuthorEntity;
import org.example.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("author")
@Slf4j
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping("add")
    public ResponseEntity<?> addAuthor(@RequestBody AuthorEntity authorEntity){
        authorRepository.save(authorEntity);
        log.info("author saved: {}", authorEntity);
        return ResponseEntity.ok(authorEntity);
    }
}
