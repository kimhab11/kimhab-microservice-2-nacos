package org.example.fallback;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.bean.res.BookResponse;
import org.example.IBook;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
public class IBookFallbackImp implements IBook {
    private Throwable cause;
    @Override
    public BookResponse getBookById(Long id) {
        log.error("feign fallback:{}", cause);
        return null;
    }
}
