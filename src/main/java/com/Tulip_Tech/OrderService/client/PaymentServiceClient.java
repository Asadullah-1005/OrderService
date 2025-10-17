package com.Tulip_Tech.OrderService.client;

import com.Tulip_Tech.OrderService.model.Dto.CreatePaymentRequest;
import com.Tulip_Tech.OrderService.model.domain.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component

public class PaymentServiceClient {


    private final WebClient webClient;


    public PaymentServiceClient(@Qualifier("paymentWebClient") WebClient webClient) {
        this.webClient = webClient;
    }


    public void doPayment(CreatePaymentRequest createPaymentRequest) {
        webClient.post()
                .uri("/doPayment")
                .bodyValue(createPaymentRequest)
                .retrieve()
                .bodyToMono(ProblemDetail.class)
                .block();
    }

    public Order.PaymentDetails getPaymentByOrderId(Long orderId) {
        return webClient.get()
                .uri("/{id}", orderId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new RuntimeException(body))))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new RuntimeException(body))))
                .bodyToMono(Order.PaymentDetails.class)
                .block();

    }
}
