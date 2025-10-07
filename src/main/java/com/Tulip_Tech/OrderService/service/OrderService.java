package com.Tulip_Tech.OrderService.service;

import com.Tulip_Tech.OrderService.model.CreateOrderRequest;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<?> placeOrder(CreateOrderRequest createOrderRequest);
}
