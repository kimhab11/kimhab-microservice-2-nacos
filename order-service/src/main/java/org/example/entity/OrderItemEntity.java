package org.example.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Data
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long bookId;
    private Integer quantity;
    private Float totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
