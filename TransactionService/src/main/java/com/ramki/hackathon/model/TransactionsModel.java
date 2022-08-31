package com.ramki.hackathon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class TransactionsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long transactionId;
	String userID;
	@Column(length = 2048)
	String message;
	String schemaValidation;
	String accountValidation;
	String transactionStatus = "initiated";
	@Column(length = 2048)
	String mxDocument;

	public TransactionsModel(long transactionId, String userID, String message, String schemaValidation,
			String accountValidation, String transactionStatus) {
		super();
		this.transactionId = transactionId;
		this.userID = userID;
		this.message = message;
		this.schemaValidation = schemaValidation;
		this.accountValidation = accountValidation;
		this.transactionStatus = transactionStatus;
	}

	public TransactionsModel() {
		super();
	}

	public TransactionsModel(String userID, String message) {
		super();
		this.userID = userID;
		this.message = message;
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

	public void setSchemaValidation(String schemaValidation) {
		this.schemaValidation = schemaValidation;
	}

	public String getAccountValidation() {
		return accountValidation;
	}

	public void setAccountValidation(String accountValidation) {
		this.accountValidation = accountValidation;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getMxDocument() {
		return mxDocument;
	}

	public void setMxDocument(String mxDocument) {
		this.mxDocument = mxDocument;
	}

}
