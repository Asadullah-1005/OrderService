package com.Tulip_Tech.OrderService.model.Dto;

import com.Tulip_Tech.OrderService.model.Payment_Mode;

public record CreatePaymentRequest(Long orderId,
                                   Long amount,
                                   Payment_Mode paymentMode,
                                   String referenceNumber
                    ) {
}
