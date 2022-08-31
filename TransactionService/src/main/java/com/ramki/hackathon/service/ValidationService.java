package com.ramki.hackathon.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ramki.common.model.AccountValidateModel;
import com.ramki.common.model.TransactionEventModel;
import com.ramki.hackathon.model.TransactionsModel;
import com.ramki.hackathon.producer.EventProducer;
import com.ramki.hackathon.repository.TransactionsRepository;

@Service
public class ValidationService {

	@Autowired
	EventProducer producer;

	@Autowired
	TransactionsRepository transactionRepository;

	public void validate(TransactionEventModel transactionEventModel) {
		AccountValidateModel account = new AccountValidateModel(transactionEventModel.getTransactionId(),
				transactionEventModel.getUserID(), transactionEventModel.getTransactionAmount(),
				transactionEventModel.getUserAccount());
		Optional<TransactionsModel> transactionsModel = transactionRepository
				.findById(transactionEventModel.getTransactionId());
		transactionsModel.get().setMxDocument(transactionEventModel.getMxDocument().toString());
		transactionsModel.get().setSchemaValidation("success");
		transactionRepository.save(transactionsModel.get());

		try {
			producer.sendAccountMessage(account);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
