package com.SurveyApp.userProfile.userClaimReward.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SurveyApp.transactionsManager.commum.model.AppTransaction;
import com.SurveyApp.userProfile.userClaimReward.repository.ClaimRewardRepository;

@Service
public class ClaimRewardService {
        
    @Autowired
    ClaimRewardRepository repo;

    public Optional<AppTransaction> getLateDailyRewardTransaction(long latestClaimedRewardId){ 
        if(latestClaimedRewardId <= 0)
            throw new IllegalArgumentException("Id of latestClaimedReward must be grather than 0");
        
        return repo.findById(latestClaimedRewardId);

        
    

    }





    public ClaimRewardRepository getRepo() {
        return repo;
    }





    public void setRepo(ClaimRewardRepository repo) {
        this.repo = repo;
    }



    
}
