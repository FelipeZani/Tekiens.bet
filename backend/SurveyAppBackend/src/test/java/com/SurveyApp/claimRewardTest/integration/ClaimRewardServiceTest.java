package com.SurveyApp.claimRewardTest.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.SurveyApp.transactionsManager.commum.model.AppTransaction;
import com.SurveyApp.transactionsManager.commum.model.AppTransactionStatus;
import com.SurveyApp.userProfile.userClaimReward.repository.ClaimRewardRepository;
import com.SurveyApp.userProfile.userClaimReward.service.ClaimRewardService;

@SpringBootTest
public class ClaimRewardServiceTest {
    @Autowired
    ClaimRewardService claimRewardService;
    
    @MockitoBean
    ClaimRewardRepository claimRewardRepository;
    
    @Test public void expectedIllegalArgumentExceptionTest(){ //test negative Id 
        when(claimRewardRepository.findById(-1L))
        .thenReturn(Optional.of(new AppTransaction(123, 1, 1, 1, AppTransactionStatus.COMPLETED)));
        
        assertThrows(IllegalArgumentException.class, ()-> claimRewardService.getLateDailyRewardTransaction(-1));

    }


    @Test public void getTransactionByIdInDataBase(){ //rest service returned parameters
        when(claimRewardRepository.findById(123l))
        .thenReturn(Optional.of(new AppTransaction(123, 1, 1, 1, AppTransactionStatus.COMPLETED)));
        
        when(claimRewardService.getLateDailyRewardTransaction(123))
        .thenReturn(Optional.of(new AppTransaction(123, 1, 1, 1, AppTransactionStatus.COMPLETED)));


        Optional<AppTransaction> trx = claimRewardService.getLateDailyRewardTransaction(123l);
        Optional<AppTransaction> trx2 = claimRewardService.getLateDailyRewardTransaction(2);
        assertAll("Testing Service ReturnedParameters",
            ()-> assertTrue(trx.isPresent()),
            ()-> assertTrue(trx2.isEmpty()),
            ()-> assertEquals(trx.get().getSrcId(), 123),
            ()-> assertEquals(trx.get().getDstId(), 1),
            ()-> assertEquals(trx.get().getTransactionTime(), 1),
            ()-> assertEquals(trx.get().getAmount(), 1),
            ()-> assertNotNull(trx.get().getStatus())
        );        
        
        



    }

    
}
