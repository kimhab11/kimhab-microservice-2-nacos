package org.example.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class CreateOrderItemRequest {
    private long userId;
    @NotEmpty
    private Set<Book> books;

    @Data
    static public class Book {
        @NotEmpty
        private Long bookId;
        @NotEmpty
        private Long quantity;
//        @NotEmpty
//        private float price;
    }
}

