package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.example.bean.res.BookResponse;
import org.example.entity.OrderItemEntity;

@Data
public class OrderItemResponse {
    @JsonIgnore
    private Long bookId;
    private BookResponse bookResponse;
    private Integer quantity;
    private Float totalPrice;

    OrderItemResponse(OrderItemEntity orderItemEntity){
        bookId = orderItemEntity.getBookId();
        bookResponse = new BookResponse();
        quantity = orderItemEntity.getQuantity();
        totalPrice = orderItemEntity.getTotalPrice();
    }
}
