package com.Tulip_Tech.OrderService.entity;

import com.Tulip_Tech.OrderService.model.Payment_Mode;
import jakarta.persistence.*;
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
    @Column(name = "product_id")
    private Long productId;

    @Setter
    private Long quantity;

    @Setter
    private String orderStatus;

    @Setter
    private Instant orderDate;

    @Setter
    private Long totalAmount;

    @Setter
    @Enumerated(EnumType.STRING)
    private Payment_Mode payment_mode;
}
