package com.Tulip_Tech.OrderService.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Map;

@Getter
public class CustomException extends RuntimeException {
    private final HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }


    public CustomException(Map<String, Object> message, HttpStatusCode statusCode) {
        super(message.toString());
        this.httpStatus = (HttpStatus) statusCode;
    }
}
