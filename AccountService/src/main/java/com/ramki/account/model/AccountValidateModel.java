package com.ramki.account.model;

public class AccountValidateModel {
	long transactionId;
	String userID;
	long transactionAmount;
	String userAccount;
	String accountValidation;

	public AccountValidateModel() {
		super();
	}

	public AccountValidateModel(long transactionId, String userID, long transactionAmount, String userAccount) {
		super();
		this.transactionId = transactionId;
		this.userID = userID;
		this.transactionAmount = transactionAmount;
		this.userAccount = userAccount;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public long getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(long transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getAccountValidation() {
		return accountValidation;
	}

	public void setAccountValidation(String accountValidation) {
		this.accountValidation = accountValidation;
	}

}
