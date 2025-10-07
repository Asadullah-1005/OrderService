package com.Tulip_Tech.OrderService.model;

public record CreateOrderRequest(long productId, long totalAmount, long quantity, Payment_Mode paymentMode) {
}
