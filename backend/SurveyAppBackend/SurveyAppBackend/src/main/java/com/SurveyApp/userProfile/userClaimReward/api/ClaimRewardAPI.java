package com.SurveyApp.userProfile.userClaimReward.api;

import com.SurveyApp.transactionsManager.commum.model.AppTransaction;

public interface ClaimRewardAPI {

    public AppTransaction claimDailyReward(long latestClaimedRewardId, long userId);

}
