package com.example.contractspayments.dto.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data

public class PaymentDtoRequest {

    @NotNull(message = "Сумма обязательна")
    @Positive(message = "Сумма не должна быть минусовой")
    private BigDecimal amount;

    @NotNull(message = "ID договора обязателен")
    private Long contractId;
}
