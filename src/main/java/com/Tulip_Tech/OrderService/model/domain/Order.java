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
    private Long orderId;
    private Long productId;
    private Long quantity;
    private Instant orderDate;
    private Long totalAmount;
    private Payment_Mode payment_mode;
    private ProductDetails productDetails;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductDetails {

        private Long id;
        private String productName;
        private long price;
        private long quantity;
    }

}
