package com.Tulip_Tech.OrderService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Table(name= "orders_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OrderEntity {

    @Id
    @GeneratedValue
    private Long orderId;

    @Setter
    private Long productId;

    @Setter
    private Long quantity;

    @Setter
    private String orderStatus;

    @Setter
    private Instant orderDate;

    @Setter
    private Long amount;
}
