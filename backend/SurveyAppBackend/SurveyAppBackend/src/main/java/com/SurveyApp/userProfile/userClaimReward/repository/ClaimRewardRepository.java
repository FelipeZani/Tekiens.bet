package com.SurveyApp.userProfile.userClaimReward.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import com.SurveyApp.transactionsManager.commum.model.AppTransaction;

@Repository
public interface ClaimRewardRepository extends JpaRepository<AppTransaction,Long>{
    @NativeQuery("SELECT * FROM app_transaction WHERE source_id = ?1")
    public List<AppTransaction> findAllBySrc(long srcId);
    
} 