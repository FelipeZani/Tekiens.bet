package com.SurveyApp.transactionsManager.transactionFactory;

import com.SurveyApp.transactionsManager.commum.model.AppTransaction;
import com.SurveyApp.transactionsManager.commum.model.AppTransactionStatus;

public interface AppTransactionFactory {

  public AppTransaction createTransaction(long srcId, long dstId,long claimedTime,int amount, AppTransactionStatus status);  
} 