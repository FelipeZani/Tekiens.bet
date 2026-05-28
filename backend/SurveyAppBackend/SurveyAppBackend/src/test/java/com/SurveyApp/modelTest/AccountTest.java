package com.SurveyApp.modelTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.SurveyApp.transactionsManager.dailyReward.dailyRewardSingleton.DailyRewardFaucet;
import com.SurveyApp.userProfile.model.Account;
import com.SurveyApp.userProfile.model.User;
import com.SurveyApp.userProfile.userClaimReward.internal.ClaimRewardImpl;
import com.SurveyApp.userProfile.userClaimReward.repository.ClaimRewardRepository;
import com.SurveyApp.userProfile.userClaimReward.service.ClaimRewardService;


public class AccountTest {
      @Autowired
        ClaimRewardRepository repo;
    /**
     * balance >= 0
     * User != null
     * transactions set != null
     */
    @Test public void constructorTest(){
      
        Account acc = new Account(0, 
            new User("Robert","Rob","123","baranga@gmail.com"),
            new ClaimRewardImpl(),
            new HashSet<Long>());

        assertAll("Testing account constructor", 
            ()->assertNotNull(acc.getUser()),
            ()-> assertTrue(Integer.max(acc.getBalance(),0) >= 0),
            ()-> assertNotNull(acc.getClaimApi(), "ClaimRewardApi shouldnt be null")


        );
    }

}
