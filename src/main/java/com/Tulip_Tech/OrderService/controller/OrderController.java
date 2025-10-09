package com.Tulip_Tech.OrderService.controller;

import com.Tulip_Tech.OrderService.model.Dto.CreateOrderRequest;
import com.Tulip_Tech.OrderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/check")
    public String healthCheck(){
        return "Order Service is up and running";
    }

}
