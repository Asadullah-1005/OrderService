package com.Tulip_Tech.OrderService.client;


import com.Tulip_Tech.OrderService.exception.CustomException;
import com.Tulip_Tech.OrderService.model.domain.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component

public class ProductServiceClient {


    private final WebClient webClient;

    public ProductServiceClient(@Qualifier("productWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public void reduceQuantity(Long productId, long quantity) {
        webClient.put()
                .uri("/reduceQuantity/{id}?quantity={quantity}", productId, quantity)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new CustomException(body, HttpStatus.BAD_REQUEST)))
                )
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new CustomException(body, HttpStatus.INTERNAL_SERVER_ERROR)))
                )
                .bodyToMono(ProblemDetail.class)
                .block();
    }

    public Order.ProductDetails getProductById(Long productId){
       return webClient.get()
                .uri("/{id}", productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new CustomException(body, HttpStatus.BAD_REQUEST)))
                )
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new CustomException(body, HttpStatus.INTERNAL_SERVER_ERROR)))
                )
                .bodyToMono(Order.ProductDetails.class)
                .block();
    }
}
