package com.SurveyApp.userProfile.userSignUp.service;

import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SurveyApp.userProfile.jwtTokens.JwtToken;
import com.SurveyApp.userProfile.model.Account;
import com.SurveyApp.userProfile.model.User;
import com.SurveyApp.userProfile.repository.UserProfileRepository;
import com.SurveyApp.userProfile.userClaimReward.internal.ClaimRewardImpl;


@Service
public class UserSignUpService {
    @Autowired
    UserProfileRepository repo;
    
    @Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ClaimRewardImpl ClaimRewardImpl;

    public String signUp(User myUser) throws InvalidKeyException, SignatureException{
        String currPWD = myUser.getPassword();
		String encodedPwd = passwordEncoder.encode(currPWD);
		System.out.println(" Hashed password: " +encodedPwd);
		myUser.setPassword(encodedPwd);
		int initialBalanceValue = 0;
		
		Account acc = new Account(
		initialBalanceValue,
		myUser,
		ClaimRewardImpl,
		new HashSet<Long>());
		
		myUser.setUserAccount(acc);
		
		repo.saveAndFlush(myUser);
		return JwtToken.generateLoginToken(
				myUser.getId(), 
				myUser.getName(),
				myUser.getEmail()
			);
	}	

	
	public boolean mailAlreadyExists(String mail){
		System.out.println("Checking email existence");
		if(repo.queryEmail(mail) != null){
			System.out.println("Input Email already exists");
			return true;
		}
		System.out.println("Input Email can be saved to DB");
		return false;
	}

}
