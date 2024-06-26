package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.example.bean.res.BookResponseDTO;
import org.example.entity.OrderItemEntity;

@Data
public class OrderItemResponse {
    @JsonIgnore
    private Long bookId;
    private BookResponseDTO bookResponseDTO;
    private Integer quantity;
    private Float totalPrice;

    OrderItemResponse(OrderItemEntity orderItemEntity){
        bookId = orderItemEntity.getBookId();
        bookResponseDTO = new BookResponseDTO();
        quantity = orderItemEntity.getQuantity();
        totalPrice = orderItemEntity.getTotalPrice();
    }
}
