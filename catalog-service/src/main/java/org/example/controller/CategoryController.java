package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.CategoryEntity;
import org.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryEntity categoryEntity){
        categoryRepository.save(categoryEntity);
        log.info("categoryEntity saved: {}", categoryEntity);
        return ResponseEntity.ok(categoryEntity);
    }
}
