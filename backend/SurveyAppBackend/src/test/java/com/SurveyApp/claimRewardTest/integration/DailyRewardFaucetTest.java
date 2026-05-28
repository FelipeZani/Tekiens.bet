package com.SurveyApp.claimRewardTest.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.SurveyApp.transactionsManager.commum.model.AppTransaction;
import com.SurveyApp.transactionsManager.commum.model.AppTransactionStatus;
import com.SurveyApp.transactionsManager.dailyReward.dailyRewardSingleton.DailyRewardFaucet;
import com.SurveyApp.transactionsManager.transactionFactory.DailyRewardFactory;

public class DailyRewardFaucetTest {

    @Test
    public void constructorTest(){
        Optional<AppTransaction> claimedTrx = Optional.ofNullable(new AppTransaction(15, 13, 20, 500, AppTransactionStatus.COMPLETED));
        DailyRewardFaucet faucet = DailyRewardFaucet.getInstance();
        faucet.setRewardFactory(new DailyRewardFactory());
        assertAll("Testing constructor",
            ()-> assertNotNull(faucet),
            ()-> assertTrue(faucet.getCoolDown()>0),
            ()->assertTrue(faucet.getDailyRewardRate() > 0),
            ()-> assertNotNull(faucet.getRewardFactory()),
            ()->assertEquals(-1,faucet.getSystemId()),
            ()->assertEquals(2500,DailyRewardFaucet.getInitialcooldown()),
            ()->assertEquals(200,DailyRewardFaucet.getInitialrewardrate()),
            ()->assertNotNull(DailyRewardFaucet.getInstance()),
            ()->assertThrows(IllegalArgumentException.class, ()-> faucet.claimReward(-1,claimedTrx))
        );

    }
    
}
