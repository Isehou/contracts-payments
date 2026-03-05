package com.example.contractspayments.repository;

import com.example.contractspayments.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByContractId(Long contractId);

}
