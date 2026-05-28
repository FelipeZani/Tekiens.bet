package com.SurveyApp.transactionsManager.transactionFactory;

import org.springframework.stereotype.Component;

import com.SurveyApp.transactionsManager.commum.model.AppTransaction;
import com.SurveyApp.transactionsManager.commum.model.AppTransactionStatus;
import com.SurveyApp.transactionsManager.dailyReward.model.DailyReward;

@Component
public class DailyRewardFactory implements AppTransactionFactory {

    @Override
    public AppTransaction createTransaction(long srcId, long dstId,long claimedTime,int amount, AppTransactionStatus status) {
        return new DailyReward(srcId,dstId,claimedTime,amount,status);
    }
    
}
