package com.example.contractspayments.dto.payment;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDtoCSV {

    public PaymentDtoCSV(String contractNumber, BigDecimal amount) {
        this.contractNumber = contractNumber;
        this.amount = amount;
    }

    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "contractNumber")
    private String contractNumber;

    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "amount")
    private BigDecimal amount;


}
