package com.SurveyApp.claimRewardTest.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.SurveyApp.transactionsManager.commum.model.AppTransaction;
import com.SurveyApp.transactionsManager.commum.model.AppTransactionStatus;
import com.SurveyApp.userProfile.userClaimReward.repository.ClaimRewardRepository;

import jakarta.transaction.Transactional;

@DataJpaTest(showSql = true, properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.h2.console.enabled=true"
})
@Transactional
public class ClaimRewardRepositoryTest {
    @Autowired
    ClaimRewardRepository repo;
    
    @BeforeEach
    public void setUp(){
        repo.save(new AppTransaction(13l, 15l, 15000l, 15, AppTransactionStatus.COMPLETED));
        repo.save(new AppTransaction(15l, 17l, 35000l, 3, AppTransactionStatus.COMPLETED));
        repo.save(new AppTransaction(30l, 13l, 25000l, 8, AppTransactionStatus.COMPLETED));
        repo.save(new AppTransaction(45l, 30l, 200000l, 15, AppTransactionStatus.COMPLETED));
        repo.flush();
    }
    @Test
    public void findRewardById() {
        Optional<AppTransaction> trx =repo.findById(123l);
        List<AppTransaction> trxBySrc =  repo.findAllBySrc(13);
        assertAll("Testing Repository", 
            () -> assertTrue(trx.isEmpty()),
            () -> assertNotNull(trxBySrc)
        );
        
    }

}
