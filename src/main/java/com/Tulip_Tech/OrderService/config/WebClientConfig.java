package com.Tulip_Tech.OrderService.config;


import com.Tulip_Tech.OrderService.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Configuration


public class WebClientConfig {

    @Value("${product.service.url}")
    private String productServiceUrl;

    @Bean
    public WebClient productWebClient(WebClient.Builder builder) {
        return builder.baseUrl(productServiceUrl).filter(errorDecoderFilter()).build();
    }

    private ExchangeFilterFunction errorDecoderFilter() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().isError()) {
                return clientResponse.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                }).flatMap(body -> Mono.error(new CustomException(body, clientResponse.statusCode())));
            }
            return Mono.just(clientResponse);
        });
    }
}
