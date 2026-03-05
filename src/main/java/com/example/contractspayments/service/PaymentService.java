package com.example.contractspayments.service;

import com.example.contractspayments.dto.payment.PaymentDtoCSV;
import com.example.contractspayments.dto.payment.PaymentDtoRequest;
import com.example.contractspayments.dto.payment.PaymentDtoResponse;
import com.example.contractspayments.entity.Contract;
import com.example.contractspayments.entity.Payment;
import com.example.contractspayments.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ContractService contractService;

    public PaymentDtoResponse create(PaymentDtoRequest dtoRequest) {
        Contract contract = contractService.throwExeption(dtoRequest.getContractId());

        Payment payment = new Payment();
        payment.setContract(contract);
        payment.setAmount(dtoRequest.getAmount());
        payment.setCreatedAt(LocalDateTime.now());

        Payment saved = paymentRepository.save(payment);
        return dtoCode(saved);
    }

    @Transactional(readOnly = true)
    public PaymentDtoResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Платёж по ID не найден"));

        return dtoCode(payment);
    }

    @Transactional(readOnly = true)
    public List<PaymentDtoResponse> getByContractId(Long contractId) {
        contractService.throwExeption(contractId);

        return paymentRepository.findByContractId(contractId)
                .stream()
                .map(this::dtoCode)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<PaymentDtoCSV> getAllForCSV() {
        return paymentRepository.findAll()
                .stream()
                .map(payment -> new PaymentDtoCSV(
                        payment.getContract().getNumber(),
                        payment.getAmount()
                ))
                .toList();

    }

    private PaymentDtoResponse dtoCode(Payment payment) {
        PaymentDtoResponse response = new PaymentDtoResponse();

        response.setId(payment.getId());
        response.setAmount(payment.getAmount());
        response.setCreatedAt(payment.getCreatedAt());
        response.setContractId(payment.getContract().getId());
        return response;
    }
}
