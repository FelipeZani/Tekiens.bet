package com.SurveyApp.transactionsManager.dailyReward.dailyRewardSingleton;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SurveyApp.transactionsManager.commum.model.AppTransaction;
import com.SurveyApp.transactionsManager.commum.model.AppTransactionStatus;
import com.SurveyApp.transactionsManager.transactionFactory.DailyRewardFactory;

@Component
public class DailyRewardFaucet {

    
    private int dailyRewardRate;
    private int coolDown;

    private final static int initialRewardRate = 200;
    private final static int initialCoolDown = 2500;
    private final int systemId = -1;

    @Autowired
    DailyRewardFactory rewardFactory;

    private static DailyRewardFaucet instance = new DailyRewardFaucet(); //eager instation

    private DailyRewardFaucet() {

        this.dailyRewardRate = initialRewardRate;
        this.coolDown = initialCoolDown;
    }


   
    public int getDailyRewardRate() {
        return dailyRewardRate;
    }

    public void setDailyRewardRate(int dailyRewardRate) {
        this.dailyRewardRate = dailyRewardRate;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }


    public static DailyRewardFaucet getInstance() {
        return instance;
    }


    public AppTransaction claimReward( long destId, Optional<AppTransaction> latestClaimReward){
        
        if(destId <= 0)
            throw new IllegalArgumentException("Destionation must be > 0");

        if(latestClaimReward.isPresent()){
            AppTransaction deftLatestClaimReward = latestClaimReward.get();
            Date now = new Date();
        
            Date allowedDate = new Date(deftLatestClaimReward.getTransactionTime()+coolDown);

            if(now.before(allowedDate)){
                return null;
            } // if after
        }
        // if == null || now.after(allowedDate)
        return rewardFactory.createTransaction(systemId, destId, new Date().getTime(), coolDown, AppTransactionStatus.COMPLETED);
    
    }



    public static int getInitialrewardrate() {
        return initialRewardRate;
    }



    public static int getInitialcooldown() {
        return initialCoolDown;
    }



    public int getSystemId() {
        return systemId;
    }



    public DailyRewardFactory getRewardFactory() {
        return rewardFactory;
    }



    public void setRewardFactory(DailyRewardFactory rewardFactory) {
        this.rewardFactory = rewardFactory;
    }



    public static void setInstance(DailyRewardFaucet instance) {
        DailyRewardFaucet.instance = instance;
    }

}
