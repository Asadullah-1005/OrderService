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
    @Column(name = "order_id")
    private Long orderId;

    @Setter
    @Column(name = "product_id")
    private Long productId;

    @Setter
    @Column(name = "quantity")
    private Long quantity;

    @Setter
    @Column(name = "order_status")
    private String orderStatus;

    @Setter
    @Column(name = "order_date")
    private Instant orderDate;

    @Setter
    @Column(name = "total_amount")
    private Long totalAmount;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode")
    private Payment_Mode payment_mode;
}
