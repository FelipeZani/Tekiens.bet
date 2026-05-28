package com.SurveyApp.userProfile.userClaimReward.internal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SurveyApp.transactionsManager.commum.model.AppTransaction;
import com.SurveyApp.transactionsManager.dailyReward.dailyRewardSingleton.DailyRewardFaucet;
import com.SurveyApp.userProfile.userClaimReward.api.ClaimRewardAPI;
import com.SurveyApp.userProfile.userClaimReward.service.ClaimRewardService;

@Component
public class ClaimRewardImpl implements ClaimRewardAPI{

    @Autowired
    ClaimRewardService service;
    @Autowired
    DailyRewardFaucet faucet;
    

    @Override
    public AppTransaction claimDailyReward(long latestClaimedRewardId, long userId ) {
        try{
            if(latestClaimedRewardId <= 0)
                throw new IllegalArgumentException("latestClailedRewardId should be > 0");
            if(userId <= 0)
                throw new IllegalArgumentException("userId should be > 0");

            Optional<AppTransaction> latestTransaction = service.getLateDailyRewardTransaction(latestClaimedRewardId);
            return faucet.claimReward(userId, latestTransaction);

        }catch(IllegalArgumentException ex){
            ex.printStackTrace();
        }
        
        return null;
    }



    public ClaimRewardService getService() {
        return service;
    }



    public void setService(ClaimRewardService service) {
        this.service = service;
    }



    public DailyRewardFaucet getFaucet() {
        return faucet;
    }



    public void setFaucet(DailyRewardFaucet faucet) {
        this.faucet = faucet;
    }
    
}
