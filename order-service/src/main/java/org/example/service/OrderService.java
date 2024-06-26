package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.IBook;
import org.example.entity.OrderEntity;
import org.example.entity.OrderItemEntity;
import org.example.model.CreateOrderItemRequest;
import org.example.model.OrderResponse;
import org.example.repository.OrderItemRepository;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    @Autowired
    private IBook iBook;

    @Transactional
    public void createOrder(CreateOrderItemRequest orderItemRequest){

        OrderEntity order = new OrderEntity();
        order.setUserId(orderItemRequest.getUserId());
        order.setStatus("N");
        var orderEntity = orderRepository.save(order);
        log.info("orderEntity: {}", orderEntity);

        orderItemRequest.getBooks().forEach(r ->{
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrder(orderEntity);

            // find book
            var book = iBook.getBookById(r.getBookId());
            orderItemEntity.setBookId(book.getId());
            orderItemEntity.setTotalPrice(r.getQuantity() * book.getPrice().floatValue());
            orderItemEntity.setQuantity(Math.toIntExact(r.getQuantity()));

            orderItemRepository.save(orderItemEntity);
            log.info("orderItemEntity: {}", orderItemEntity);
        });
    }

    public OrderResponse getOrderById(long id) {
        var order = orderRepository.findById(id).orElseThrow(()-> new RuntimeException("not found"));
        var orderRes = new OrderResponse(order);
        orderRes.getOrderItemResponses().forEach(orderItemResponse -> {
            // find book
            var book = iBook.getBookById(orderItemResponse.getBookId());
            orderItemResponse.setBookResponse(book);
        });
        log.info("orderRes: {}" , orderRes);

        return orderRes;
    }

    public List<OrderResponse> listAll() {
        var orderEntityList = orderRepository.findAll();
        log.info("orderEntityList: {}", orderEntityList.size());

        List<OrderResponse> orderResponseList = new ArrayList<>();
        orderEntityList.forEach(orderEntity -> {
            var orderRes= new OrderResponse(orderEntity);
            orderRes.getOrderItemResponses().forEach(orderItemResponse -> {
                // find book
                var book = iBook.getBookById(orderItemResponse.getBookId());
                orderItemResponse.setBookResponse(book);
            });
            orderResponseList.add(orderRes);
        });

        return orderResponseList;
    }
}
