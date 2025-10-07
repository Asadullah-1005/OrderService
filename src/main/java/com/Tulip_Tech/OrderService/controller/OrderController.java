package com.Tulip_Tech.OrderService.controller;

import com.Tulip_Tech.OrderService.model.CreateOrderRequest;
import com.Tulip_Tech.OrderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return orderService.placeOrder(createOrderRequest);
    }

}
