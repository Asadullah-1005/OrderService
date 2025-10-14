package com.Tulip_Tech.OrderService.model.Dto;

import com.Tulip_Tech.OrderService.model.Payment_Mode;

public record CreateOrderRequest(long productId,
                                 long quantity,
                                 long totalAmount,
                                 Payment_Mode payment_mode
) {
}
