package com.example.contractspayments.dto.payment;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDtoResponse {

    private Long id;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private Long contractId;
}
