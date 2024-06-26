package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.IBook;
import org.example.model.CreateOrderItemRequest;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private IBook iBook;

    @PostMapping("create")
    public ResponseEntity<?> createOrder(@Validated @RequestBody CreateOrderItemRequest orderItemRequest){
        orderService.createOrder(orderItemRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOrderById(@PathVariable long id){
        var res = orderService.getOrderById(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("list-all")
    public ResponseEntity<?> listAll(){
        var res = orderService.listAll();
        return ResponseEntity.ok(res);
    }
}
