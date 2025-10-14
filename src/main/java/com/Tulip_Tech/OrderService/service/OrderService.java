package com.Tulip_Tech.OrderService.service;

import com.Tulip_Tech.OrderService.model.Dto.CreateOrderRequest;
import com.Tulip_Tech.OrderService.model.domain.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<?> placeOrder(CreateOrderRequest createOrderRequest);

    List<Order> getAll();
}
