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

    private String orderDate;

    private List<OrderItemResponse> orderItemResponses = new ArrayList<>();

    private float subTotal;

    public OrderResponse(OrderEntity orderEntity){
        id = orderEntity.getId();
        userId = orderEntity.getUserId();
        status = orderEntity.getStatus();
        orderDate = String.valueOf(orderEntity.getOrderDate());

        orderEntity.getOrderItems().forEach(orderItemEntity -> {
            orderItemResponses.add(new OrderItemResponse(orderItemEntity));
            subTotal += orderItemEntity.getTotalPrice();
        });

    }
}
