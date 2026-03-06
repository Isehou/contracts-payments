## Тестирование через Postman
Готовые коллекции запросов для ручного тестирования API находятся в директории:
postman-test/

также в этой папке хранятся скрины самих запросов с таблица CSV

## Структура проекта

```
src/main/java/com/example/contractspayments/
├── controller/
│   ├── ContractController.java
│   └── PaymentController.java
├── dto/
│   ├── contract/
│   │   ├── ContractDtoRequest.java
│   │   └── ContractDtoResponse.java
│   └── payment/
│       ├── PaymentDtoCSV.java
│       ├── PaymentDtoRequest.java
│       └── PaymentDtoResponse.java
├── entity/
│   ├── Contract.java
│   └── Payment.java
├── repository/
│   ├── ContractRepository.java
│   └── PaymentRepository.java
├── service/
│   ├── ContractService.java
│   └── PaymentService.java
└── ContractsPaymentsApplication.java

├── postman-test/
├── src/
```
