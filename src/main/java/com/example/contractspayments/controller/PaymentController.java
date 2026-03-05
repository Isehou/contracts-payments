package com.example.contractspayments.controller;

import com.example.contractspayments.dto.payment.PaymentDtoCSV;
import com.example.contractspayments.dto.payment.PaymentDtoRequest;
import com.example.contractspayments.dto.payment.PaymentDtoResponse;
import com.example.contractspayments.service.PaymentService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentDtoResponse> create(@Valid @RequestBody PaymentDtoRequest request) {

        PaymentDtoResponse response = paymentService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDtoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentDtoResponse>> getByContractId(@RequestParam Long contractId) {
        return ResponseEntity.ok(paymentService.getByContractId(contractId));
    }

    @GetMapping("/export")
    public void exportCSV(HttpServletResponse response) throws Exception {

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"payments.csv\"");

        StatefulBeanToCsv<PaymentDtoCSV> writer = new StatefulBeanToCsvBuilder<PaymentDtoCSV>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(';')
                .withOrderedResults(true)
                .build();

        writer.write(paymentService.getAllForCSV());
    }
}
