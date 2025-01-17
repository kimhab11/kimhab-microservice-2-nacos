package org.example.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.feign.IBook;
import org.example.entity.OrderEntity;
import org.example.entity.OrderItemEntity;
import org.example.model.CreateOrderItemRequest;
import org.example.model.OrderResponse;
import org.example.repository.OrderItemRepository;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("org.example.feign.IBook")
    @Autowired
    private IBook iBook;

    @Transactional
    public void createOrder(CreateOrderItemRequest orderItemRequest) {
        OrderEntity order = new OrderEntity();
        order.setUserId(orderItemRequest.getUserId());
        order.setStatus("N");

        // Prepare a list to hold valid OrderItemEntity objects
        List<OrderItemEntity> orderItems = new ArrayList<>();

        // Flag to check if all books are valid
        boolean allBooksValid = true;

        for (var r : orderItemRequest.getBooks()) {
            // Find book
            var book = iBook.getBookById(r.getBookId());
            if (book == null) {
                allBooksValid = false;
                break;
            }

            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrder(order);
            orderItemEntity.setBookId(book.getId());
            orderItemEntity.setTotalPrice(r.getQuantity() * book.getPrice().floatValue());
            orderItemEntity.setQuantity(Math.toIntExact(r.getQuantity()));

            // Add to the list of valid order items
            orderItems.add(orderItemEntity);
        }

        if (allBooksValid) {
            var orderEntity = orderRepository.save(order);
            log.info("orderEntity: {}", orderEntity);

            // Save all valid order items
            orderItems.forEach(orderItemEntity -> {
                orderItemEntity.setOrder(orderEntity); // Set the persisted order entity
                orderItemRepository.save(orderItemEntity);
                log.info("orderItemEntity: {}", orderItemEntity);
            });
        } else {
            throw new RuntimeException("One or more books are null");
        }
    }


    //    @CircuitBreaker(name = "catalogService")
//    @Retry(name = "catalogService")
//    @RateLimiter(name = "catalogService")
//    @Bulkhead(name = "catalogService")
    public OrderResponse getOrderById(long id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        var orderRes = new OrderResponse(order);
        orderRes.getOrderItemResponses().forEach(orderItemResponse -> {
            // find book
            var book = iBook.getBookById(orderItemResponse.getBookId());
            orderItemResponse.setBookResponseDTO(book);
        });
        log.info("orderRes: {}", orderRes);

        return orderRes;
    }

    public List<OrderResponse> listAll() {
        var orderEntityList = orderRepository.findAll();
        log.info("orderEntityList: {}", orderEntityList.size());

        List<OrderResponse> orderResponseList = new ArrayList<>();
        orderEntityList.forEach(orderEntity -> {
            var orderRes = new OrderResponse(orderEntity);
            orderRes.getOrderItemResponses().forEach(orderItemResponse -> {
                // find book
                var book = iBook.getBookById(orderItemResponse.getBookId());
                orderItemResponse.setBookResponseDTO(book);
            });
            orderResponseList.add(orderRes);
        });

        return orderResponseList;
    }
}
