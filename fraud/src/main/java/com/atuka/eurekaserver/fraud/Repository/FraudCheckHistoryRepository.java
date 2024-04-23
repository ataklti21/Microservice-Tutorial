package com.atuka.eurekaserver.fraud.Repository;

import com.atuka.eurekaserver.fraud.Model.FraudCheckHistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudCheckHistoryRepository
        extends JpaRepository<FraudCheckHistoryModel,Integer> {
}
