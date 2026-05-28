package com.SurveyApp.userProfile.model;

import java.util.HashSet;
import java.util.Set;

import com.SurveyApp.userProfile.userClaimReward.api.ClaimRewardAPI;
import com.SurveyApp.userProfile.userClaimReward.internal.ClaimRewardImpl;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int balance;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Transient
	ClaimRewardAPI claimReward;

	@ElementCollection
	@CollectionTable(
        name="transaction_historic",
        joinColumns=@JoinColumn(name="user_id")
  	)
	@Column()
	Set<Long> trxHistoric;

	public Account() {}
	public Account(int balance, User user, ClaimRewardImpl claimRewardAPI, HashSet<Long> trxHist) {

		if (balance < 0)
			throw new IllegalArgumentException("balance should be >=0");
		if (user == null)
			throw new IllegalArgumentException("user shouldn't be null");
		if (claimRewardAPI == null)
			throw new IllegalArgumentException("ClaimRewardAPI shouldnt be null");

		this.balance = balance;
		this.user = user;
		this.claimReward = claimRewardAPI;
		this.trxHistoric = trxHist;
	}

	public int getId() {
		return id;
	}

	public Long getLastClaimedRewardId(){
		

		if(trxHistoric != null){	

			int currentIndex = 0;
			int desiredIndex = trxHistoric.size() >= 1 ? trxHistoric.size() -1 : currentIndex;

			for( Long element :trxHistoric){
				if(currentIndex == desiredIndex)
					return element;
			}
		}
		
		return null;
	}



	public void setId(int id) {
		this.id = id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + balance;
		result = prime * result + trxHistoric.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id != other.id)
			return false;
		if (balance != other.balance)
			return false;
		if (! trxHistoric.equals(other.trxHistoric))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ClaimRewardAPI getClaimApi() {
		return claimReward;
	}

	public void setClaimApi(ClaimRewardAPI claimReward) {
		this.claimReward = claimReward;
	}

	public Set<Long> getTrxHistoric() {
		return trxHistoric;
	}

	public void setTrxHistoric(Set<Long> trxHistoric) {
		this.trxHistoric = trxHistoric;
	}

}
