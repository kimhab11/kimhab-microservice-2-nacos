package org.example.feign.fallback;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.bean.res.BookResponseDTO;
import org.example.feign.IBook;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
public class IBookFallbackImp implements IBook {
    private Throwable cause;
    @Override
    public BookResponseDTO getBookById(Long id) {
        log.error("feign fallback: {}", cause.getLocalizedMessage());
        return null;
    }
}
