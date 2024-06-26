package org.example.feign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.example.feign.IBook;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IBookFallbackFactory implements FallbackFactory<IBook> {
    @Override
    public IBook create(Throwable cause) {
        IBookFallbackImp iBookFallbackImp = new IBookFallbackImp();
        iBookFallbackImp.setCause(cause);
        log.error("IBookFallbackFactory:, {}", cause.getMessage());

        return iBookFallbackImp;
    }
}
