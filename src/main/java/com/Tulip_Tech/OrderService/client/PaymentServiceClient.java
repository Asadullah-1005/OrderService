package com.Tulip_Tech.OrderService.client;

import com.Tulip_Tech.OrderService.model.Dto.CreatePaymentRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

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
}
