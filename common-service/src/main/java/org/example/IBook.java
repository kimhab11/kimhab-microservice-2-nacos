package org.example;

import org.example.bean.res.BookResponse;
import org.example.fallback.IBookFallbackImp;
import org.example.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalog-service", path = "book", fallbackFactory = IBookFallbackImp.class, configuration = FeignConfig.class)
public interface IBook {
    @GetMapping("/{id}")
    BookResponse getBookById(@PathVariable("id") Long id);
}