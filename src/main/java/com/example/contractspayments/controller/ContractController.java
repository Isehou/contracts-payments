package com.example.contractspayments.controller;

import com.example.contractspayments.dto.contract.ContractDtoRequest;
import com.example.contractspayments.dto.contract.ContractDtoResponse;
import com.example.contractspayments.service.ContractService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }


    @PostMapping
    public ResponseEntity<ContractDtoResponse> create(@Valid @RequestBody ContractDtoRequest request) {

        ContractDtoResponse response = contractService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDtoResponse> getById(@PathVariable Long id) {

        ContractDtoResponse response = contractService.getContractById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ContractDtoResponse>> getAllContract() {

        List<ContractDtoResponse> responses = contractService.getAllContracts();

        return ResponseEntity.of(Optional.ofNullable(responses));
    }
}
