package com.example.contractspayments.service;

import com.example.contractspayments.dto.payment.PaymentDtoRequest;
import com.example.contractspayments.dto.payment.PaymentDtoResponse;
import com.example.contractspayments.entity.Payment;
import com.example.contractspayments.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentDtoResponse create(PaymentDtoRequest dtoRequest) {

    }

    @Transactional(readOnly = true)
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Платёж по ID не найден"));
    }

    @Transactional(readOnly = true)
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
