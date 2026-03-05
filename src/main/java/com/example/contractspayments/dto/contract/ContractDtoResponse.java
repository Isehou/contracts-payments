package com.example.contractspayments.dto.contract;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ContractDtoResponse {

    private Long id;
    private String number;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
}
