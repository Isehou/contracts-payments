package com.example.contractspayments.dto.contract;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContractDtoRequest {

    @NotBlank(message = "Номер договора обязателен")
    private String number;

    @NotNull(message = "Сумма обязательна")
    @Positive(message = "Сумма не должна быть минусовой")
    private BigDecimal totalAmount;
}
