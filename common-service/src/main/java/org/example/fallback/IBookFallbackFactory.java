package org.example.fallback;

import lombok.extern.slf4j.Slf4j;
import org.example.IBook;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IBookFallbackFactory implements FallbackFactory<IBook> {
    @Override
    public IBook create(Throwable cause) {
        IBookFallbackImp iBookFallbackImp = new IBookFallbackImp();
        iBookFallbackImp.setCause(cause);
        log.error("IBookFallbackFactory: {}", iBookFallbackImp);

        return iBookFallbackImp;
    }
}
