package com.example.contractspayments.service;

import com.example.contractspayments.dto.contract.ContractDtoRequest;
import com.example.contractspayments.dto.contract.ContractDtoResponse;
import com.example.contractspayments.entity.Contract;
import com.example.contractspayments.repository.ContractRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractDtoResponse create(ContractDtoRequest dtoRequest) {
        Contract contract = new Contract();
        contract.setNumber(dtoRequest.getNumber());
        contract.setTotalAmount(dtoRequest.getTotalAmount());
        contract.setCreatedAt(LocalDateTime.now());

        Contract saved = contractRepository.save(contract);

        ContractDtoResponse response = new ContractDtoResponse();


    }

    @Transactional(readOnly = true)
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Договор по ID не найден"));
    }

    @Transactional(readOnly = true)
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }
}
