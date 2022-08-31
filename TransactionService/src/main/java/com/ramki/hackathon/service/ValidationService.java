package com.ramki.hackathon.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ramki.hackathon.model.AccountValidateModel;
import com.ramki.hackathon.model.TransactionEventModel;
import com.ramki.hackathon.model.TransactionsModel;
import com.ramki.hackathon.producer.EventProducer;
import com.ramki.hackathon.repository.TransactionsRepository;

@Service
public class ValidationService {

	@Autowired
	EventProducer producer;

	@Autowired
	TransactionsRepository transactionRepository;

	static final Logger log = LoggerFactory.getLogger(ValidationService.class);

	public void validate(TransactionEventModel transactionEventModel) {
		Optional<TransactionsModel> transactionsModel = transactionRepository
				.findById(transactionEventModel.getTransactionId());
		if (!transactionEventModel.getSchemaValidation().contentEquals("failed")) {

			AccountValidateModel account = new AccountValidateModel(transactionEventModel.getTransactionId(),
					transactionEventModel.getUserID(), transactionEventModel.getTransactionAmount(),
					transactionEventModel.getUserAccount());
			transactionsModel.get().setMxDocument(transactionEventModel.getMxDocument());
			transactionsModel.get().setSchemaValidation("success");
			try {
				producer.sendAccountMessage(account);
			} catch (JsonProcessingException e) {
				log.error("Error in ValidationService at method validate. " + e.getMessage());
			}
		} else {
			transactionsModel.get().setSchemaValidation("failed");
			transactionsModel.get().setTransactionStatus("canceled");
		}

		transactionRepository.save(transactionsModel.get());

	}

}
