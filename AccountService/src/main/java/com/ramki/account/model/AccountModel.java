package com.ramki.account.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class AccountModel {

	@Id
	String userID;
	long accountBalance;
	String userAccount;

	public AccountModel() {
		super();
	}

	public AccountModel(String userID, long accountBalance, String userAccount) {
		super();
		this.userID = userID;
		this.accountBalance = accountBalance;
		this.userAccount = userAccount;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public long getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

}
