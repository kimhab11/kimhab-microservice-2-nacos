package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.OrderEntity;
import org.example.entity.OrderItemEntity;
import org.example.model.CreateOrderItemRequest;
import org.example.repository.OrderItemRepository;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @PostMapping("create")
    public ResponseEntity<?> createOrder(@Validated @RequestBody CreateOrderItemRequest orderItemRequest){

        OrderEntity order = new OrderEntity();
        order.setUserId(orderItemRequest.getUserId());
        var orderEntity = orderRepository.save(order);
        log.info("orderEntity: {}", orderEntity);

        orderItemRequest.getBooks().forEach(r ->{
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrder(orderEntity);
            orderItemEntity.setBookId(r.getBookId());
            orderItemEntity.setQuantity(Math.toIntExact(r.getQuantity()));
            orderItemEntity.setTotalPrice(r.getQuantity() * r.getPrice());
            orderItemRepository.save(orderItemEntity);
            log.info("orderItemEntity: {}", orderItemEntity);
        });

        return ResponseEntity.ok("create order");

    }
}
