package com.Tulip_Tech.OrderService.service;


import com.Tulip_Tech.OrderService.entity.OrderEntity;
import com.Tulip_Tech.OrderService.exception.CustomException;
import com.Tulip_Tech.OrderService.model.CreateOrderRequest;
import com.Tulip_Tech.OrderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    @Override
    public ResponseEntity<?> placeOrder(CreateOrderRequest createOrderRequest) {

        //reduce the quantity of product
        try {
            String productResponse = webClient.put()
                    .uri("/reduceQuantity/{id}?quantity={quantity}",
                            createOrderRequest.productId(),
                            createOrderRequest.quantity())
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, clientResponse ->
                            clientResponse.bodyToMono(String.class)
                                    .flatMap(body -> Mono.error(new CustomException(body,HttpStatus.BAD_REQUEST)))
                    )
                    .bodyToMono(String.class)   // get actual response body
                    .block();
            log.info("ProductService response: {}", productResponse);
            OrderEntity orderEntity = OrderEntity.builder()
                    .productId(createOrderRequest.productId())
                    .amount(createOrderRequest.totalAmount())
                    .quantity(createOrderRequest.quantity())
                    .orderStatus("CREATED")
                    .orderDate(Instant.now())
                    .build();

            orderEntity = orderRepository.save(orderEntity);
            log.info("Order Placed with orderId={}", orderEntity);
            return ResponseEntity.ok(orderEntity.getOrderId()) ;
        }catch (CustomException ex){
            log.error("Error occurred while calling ProductService: {}", ex.getMessage());
            return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
        }catch (Exception ex){
            log.error("Unexpected error occurred: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }



    }
}
