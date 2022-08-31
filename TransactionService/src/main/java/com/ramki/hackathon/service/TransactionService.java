package com.ramki.hackathon.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ramki.common.model.AccountValidateModel;
import com.ramki.common.model.TransactionEventModel;
import com.ramki.common.model.TransactionInfo;
import com.ramki.hackathon.controller.TransactionController;
import com.ramki.hackathon.model.RequestModel;
import com.ramki.hackathon.model.ResponseModel;
import com.ramki.hackathon.model.TransactionsModel;
import com.ramki.hackathon.producer.EventProducer;
import com.ramki.hackathon.repository.TransactionsRepository;

@Service
public class TransactionService {

	@Autowired
	TransactionsRepository transactionRepository;

	@Autowired
	EventProducer producer;
	
	static final Logger log = LoggerFactory.getLogger(TransactionService.class);

	public ResponseModel process(RequestModel request) {
		ResponseModel responseModel = null;
		TransactionsModel transaction = new TransactionsModel(request.getUserId(), request.getMtMessage());
		TransactionsModel transactions = transactionRepository.save(transaction);
		TransactionEventModel transactionEventModel = new TransactionEventModel(transactions.getTransactionId(),
				request.getUserId(), request.getMtMessage());

		try {
			producer.sendMessage(transactionEventModel);
			responseModel = new ResponseModel(200,
					"Transaction has been initiated. Yor transaction id is " + transactions.getTransactionId());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error in TransactionService at method process. "+e.getMessage());
		}

		return responseModel;
	}

	public void postMX(AccountValidateModel accountModel) {
		Optional<TransactionsModel> transactionsModel = transactionRepository.findById(accountModel.getTransactionId());
		if (accountModel.getAccountValidation().contains("success")) {
			transactionsModel.get().setAccountValidation("success");
			transactionsModel.get().setTransactionStatus("Processsed");
			transactionRepository.save(transactionsModel.get());
		}

	}

	public List<TransactionInfo> getAllTransactions() {
		List<TransactionsModel> passengers = (List<TransactionsModel>) transactionRepository.findAll();
		List<TransactionInfo> transactions = new ArrayList<TransactionInfo>();
		for (Iterator iterator = passengers.iterator(); iterator.hasNext();) {
			TransactionsModel transactionsModel = (TransactionsModel) iterator.next();
			TransactionInfo transactionInfo = new TransactionInfo();
			transactionInfo.setTransactionId(transactionsModel.getTransactionId());
			transactionInfo.setTransactionStatus(transactionsModel.getTransactionStatus());
			transactionInfo.setUserID(transactionsModel.getUserID());
			transactions.add(transactionInfo);
		}
		return transactions;
	}

	public TransactionInfo getTransaction(long transactionId) {
		Optional<TransactionsModel> transactionsModel = transactionRepository.findById(transactionId);
		TransactionInfo transactionInfo = new TransactionInfo();
		transactionInfo.setTransactionId(transactionsModel.get().getTransactionId());
		transactionInfo.setTransactionStatus(transactionsModel.get().getTransactionStatus());
		transactionInfo.setUserID(transactionsModel.get().getUserID());
		return transactionInfo;
	}

}
