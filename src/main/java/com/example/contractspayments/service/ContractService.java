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

        return dtoCode(saved);
    }

    @Transactional(readOnly = true)
    public ContractDtoResponse getContractById(Long id) {

        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Договор по ID не найден"));

        return dtoCode(contract);
    }

    @Transactional(readOnly = true)
    public List<ContractDtoResponse> getAllContracts() {
        return contractRepository.findAll()
                .stream()
                .map(this::dtoCode)
                .toList();
    }

    private ContractDtoResponse dtoCode(Contract contract) {
        ContractDtoResponse response = new ContractDtoResponse();

        response.setId(contract.getId());
        response.setNumber(contract.getNumber());
        response.setTotalAmount(contract.getTotalAmount());
        response.setCreatedAt(contract.getCreatedAt());
        return response;
    }

    public Contract throwExeption(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Договор не найден" + id));
    }
}
