package com.ramki.hackathon.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramki.common.model.AccountValidateModel;
import com.ramki.common.model.TransactionEventModel;
import com.ramki.hackathon.service.TransactionService;
import com.ramki.hackathon.service.ValidationService;

import springfox.documentation.swagger2.mappers.ModelMapper;

@Component
public class Consumer {

	private static final String transactionTopic = "${topic.transaction_topic}";
	private static final String accountTopic = "${topic.transaction_account_topic}";

	private final ObjectMapper objectMapper;
	private final ModelMapper modelMapper;
	ValidationService validationService;
	TransactionService transactionService;

	@Autowired
	public Consumer(ObjectMapper objectMapper, ModelMapper modelMapper, ValidationService validationService,TransactionService transactionService) {
		this.objectMapper = objectMapper;
		this.modelMapper = modelMapper;
		this.validationService = validationService;
		this.transactionService = transactionService;
	}

	@KafkaListener(topics = transactionTopic)
	public void consumeMessage(String message) throws JsonProcessingException {

		TransactionEventModel transactionEventModel = objectMapper.readValue(message, TransactionEventModel.class);
		validationService.validate(transactionEventModel);
	}
	
	@KafkaListener(topics = accountTopic)
	public void consumeAccountMessage(String message) throws JsonProcessingException {

		AccountValidateModel accountModel = objectMapper.readValue(message, AccountValidateModel.class);
		transactionService.postMX(accountModel);
	}

}
