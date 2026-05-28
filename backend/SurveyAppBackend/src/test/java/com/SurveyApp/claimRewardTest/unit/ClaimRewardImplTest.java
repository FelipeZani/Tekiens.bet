package com.SurveyApp.claimRewardTest.unit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SurveyApp.transactionsManager.dailyReward.dailyRewardSingleton.DailyRewardFaucet;
import com.SurveyApp.userProfile.userClaimReward.api.ClaimRewardAPI;
import com.SurveyApp.userProfile.userClaimReward.internal.ClaimRewardImpl;
import com.SurveyApp.userProfile.userClaimReward.service.ClaimRewardService;


@SpringBootTest
class ClaimRewardImplTest {

    @Autowired
    ClaimRewardService serv;

    @Test
    void claimRewardImplTest() {

        ClaimRewardAPI claimapi =
                new ClaimRewardImpl();

        assertAll("Testing api",
            () -> assertNotNull(claimapi),
            () -> assertNotNull(serv),
            () -> assertNotNull(serv.getRepo()),
            () -> assertNotNull(DailyRewardFaucet.getInstance())
        );
    }
}