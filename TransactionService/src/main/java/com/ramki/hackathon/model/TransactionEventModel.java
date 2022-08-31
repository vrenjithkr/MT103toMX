package com.ramki.hackathon.model;

import com.ramki.hackathon.model.pacs008.Document;

public class TransactionEventModel {

	long transactionId;
	String userID;
	String message;
	String schemaValidation;
	String accountValidation;
	Document mxDocument;
	long transactionAmount;
	String userAccount;

	public TransactionEventModel() {
		super();
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSchemaValidation() {
		return schemaValidation;
	}

	public Document getMxDocument() {
		return mxDocument;
	}

	public void setMxDocument(Document mxDocument) {
		this.mxDocument = mxDocument;
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

	public void setUserAccount(String string) {
		this.userAccount = string;
	}

	public void setSchemaValidation(String schemaValidation) {
		this.schemaValidation = schemaValidation;
	}

	public String getAccountValidation() {
		return accountValidation;
	}

	public void setAccountValidation(String accountValidation) {
		this.accountValidation = accountValidation;
	}

	public TransactionEventModel(long transactionId, String userID, String message) {
		super();
		this.transactionId = transactionId;
		this.userID = userID;
		this.message = message;
	}

}
