package org.example.feign;

import org.example.bean.res.BookResponseDTO;
import org.example.feign.fallback.IBookFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalog-service", path = "book", fallbackFactory = IBookFallbackFactory.class,configuration = FeignConfig.class)
public interface IBook {
    @GetMapping("/{id}")
    BookResponseDTO getBookById(@PathVariable("id") Long id);
}