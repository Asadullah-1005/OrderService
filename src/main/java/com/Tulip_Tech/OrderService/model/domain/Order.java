package com.Tulip_Tech.OrderService.model.domain;

import com.Tulip_Tech.OrderService.model.Payment_Mode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private Long productId;
    private Long quantity;
    private Instant orderDate;
    private Long amount;
    private Payment_Mode paymentMode;

}
