package org.example.model;

import lombok.Data;
import org.example.entity.OrderItemEntity;

@Data
public class OrderItemResponse {
    private Long bookId;
    private Integer quantity;
    private Float totalPrice;

    OrderItemResponse(OrderItemEntity orderItemEntity){
        bookId = orderItemEntity.getBookId();
        quantity = orderItemEntity.getQuantity();
        totalPrice = orderItemEntity.getTotalPrice();
    }
}
