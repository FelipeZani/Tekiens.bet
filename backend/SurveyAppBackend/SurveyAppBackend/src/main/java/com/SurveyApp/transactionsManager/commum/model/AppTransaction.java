package com.SurveyApp.transactionsManager.commum.model;

import static java.lang.Math.toIntExact;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity(name = "app_transaction")
public class AppTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;

	@Column(name = "source_id", updatable = false)
	protected long srcId;

	@Column(name = "destination_id", updatable = false)
	protected long dstId;

	@Column(updatable = false)
	protected int amount;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	protected AppTransactionStatus status;
	
	@Column(name = "transaction_time")
	protected long transactionTime;

	public AppTransaction (long srcId, long dstId,long transactionTime,int amount, AppTransactionStatus status) {

		if (srcId <= 0)
			throw new IllegalArgumentException("srcId must be > 0");
		if (dstId <= 0)
			throw new IllegalArgumentException("dstId must be > 0");
		if (amount < 1)
			throw new IllegalArgumentException("amount must be >= 1");
		if (status == null)
			throw new IllegalArgumentException("status cannot be null");

		this.srcId = srcId;
		this.dstId = dstId;
		this.amount = amount;
		this.status = status;
		this.transactionTime = transactionTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSrcId() {
		return srcId;
	}

	public void setSrcId(long srcId) {
		this.srcId = srcId;
	}

	public long getDstId() {
		return dstId;
	}

	public void setDstId(long dstId) {
		this.dstId = dstId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public AppTransactionStatus getStatus() {
		return status;
	}

	public void setStatus(AppTransactionStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", srcId=" + srcId + ", dstId=" + dstId + ", amount=" + amount
				+ ", status="
				+ status.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + toIntExact(id);
		result = prime * result + toIntExact(srcId);
		result = prime * result + toIntExact(dstId);
		result = prime * result + amount;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		AppTransaction other = (AppTransaction) obj;
		if (id != other.id)
			return false;
		if (srcId != other.srcId)
			return false;
		if (dstId != other.dstId)
			return false;
		if (amount != other.amount)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	public long getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(long transactionTime) {
		this.transactionTime = transactionTime;
	}

}
