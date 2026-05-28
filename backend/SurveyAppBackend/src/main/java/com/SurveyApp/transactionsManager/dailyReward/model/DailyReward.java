package com.SurveyApp.transactionsManager.dailyReward.model;

import com.SurveyApp.transactionsManager.commum.model.AppTransaction;
import com.SurveyApp.transactionsManager.commum.model.AppTransactionStatus;

public class DailyReward extends AppTransaction{

    public DailyReward(long srcId, long dstId,long claimedTime,int amount, AppTransactionStatus status) {
        super(srcId, dstId, claimedTime,amount, status);
        
    }
    
}
