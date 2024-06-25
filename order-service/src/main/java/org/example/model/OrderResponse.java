package org.example.model;

import lombok.Data;
import org.example.entity.OrderEntity;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private long userId;
    private String status;

    private List<OrderItemResponse> orderItemResponses = new ArrayList<>();

    public OrderResponse(OrderEntity orderEntity){
        id = orderEntity.getId();
        userId = orderEntity.getUserId();
        status = orderEntity.getStatus();

        orderEntity.getOrderItems().forEach(orderItemEntity -> {
            orderItemResponses.add(new OrderItemResponse(orderItemEntity));
        });

    }
}
