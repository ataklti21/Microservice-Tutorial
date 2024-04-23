package com.atuka.eurekaserver.fraud.Service;

import com.atuka.eurekaserver.fraud.Model.FraudCheckHistoryModel;
import com.atuka.eurekaserver.fraud.Repository.FraudCheckHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckHistoryService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;


    public boolean isFraudulentCustomer(Integer customerId){
        fraudCheckHistoryRepository.save(
                FraudCheckHistoryModel.builder()
                        .customerId(customerId)
                        .isFraudSter(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
