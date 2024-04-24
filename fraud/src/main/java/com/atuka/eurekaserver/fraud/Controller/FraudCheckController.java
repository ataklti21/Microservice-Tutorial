package com.atuka.eurekaserver.fraud.Controller;

import com.atuka.clients.faurd.Respose.FraudCheckResponse;
import com.atuka.eurekaserver.fraud.Service.FraudCheckHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudCheckController {
    private final FraudCheckHistoryService fraudCheckHistoryService;
    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster
            (@PathVariable("customerId") Integer customerId) {
        boolean isFraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);
        log.info("Fraud check for the customer {}",customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
