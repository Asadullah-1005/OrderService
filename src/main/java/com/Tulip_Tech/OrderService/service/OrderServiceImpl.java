package com.Tulip_Tech.OrderService.service;


import com.Tulip_Tech.OrderService.client.PaymentServiceClient;
import com.Tulip_Tech.OrderService.client.ProductServiceClient;
import com.Tulip_Tech.OrderService.entity.OrderEntity;
import com.Tulip_Tech.OrderService.exception.CustomException;
import com.Tulip_Tech.OrderService.mapper.OrderMapper;
import com.Tulip_Tech.OrderService.model.Dto.CreateOrderRequest;
import com.Tulip_Tech.OrderService.model.Dto.CreatePaymentRequest;
import com.Tulip_Tech.OrderService.model.domain.Order;
import com.Tulip_Tech.OrderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;
    private final PaymentServiceClient paymentServiceClient;
    private final OrderMapper orderMapper;

    @Override
    public ResponseEntity<?> placeOrder(CreateOrderRequest createOrderRequest) {

        //reduce the quantity of product
        try {
            log.info("Calling ProductService to reduce quantity for productId: {}", createOrderRequest.productId());

            productServiceClient.reduceQuantity(createOrderRequest.productId(), createOrderRequest.quantity());

            log.info("Product quantity reduced successfully for productId: {}", createOrderRequest.productId());

            OrderEntity orderEntity = orderMapper.createOrderEntity(createOrderRequest);
            orderRepository.save(orderEntity);

            log.info("calling payment service to complete the payment");
            CreatePaymentRequest request = new CreatePaymentRequest(orderEntity.getOrderId(),orderEntity.getTotalAmount(), orderEntity.getPayment_mode(),"Me");
            paymentServiceClient.doPayment(request);

            log.info("Order Placed with orderId={}", orderEntity);
            return ResponseEntity.ok(orderEntity.getOrderId());
        } catch (CustomException ex) {
            log.error("Error occurred while calling ProductService: {}", ex.getMessage());
            return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
        } catch (Exception ex) {
            log.error("Unexpected error occurred: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }


    }

    @Override
    public List<Order> getAll() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntities.stream().map(orderMapper::EntityToOrder).toList();
    }
}
