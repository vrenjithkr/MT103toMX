package com.ramki.account.consumer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramki.account.model.AccountValidateModel;
import com.ramki.account.service.ValidationService;

@Component
public class Consumer {

	private static final String accountTopic = "${topic.account_topic}";

	private final ObjectMapper objectMapper;
	private final ModelMapper modelMapper;
	ValidationService validationService;

	@Autowired
	public Consumer(ObjectMapper objectMapper, ModelMapper modelMapper, ValidationService validationService) {
		this.objectMapper = objectMapper;
		this.modelMapper = modelMapper;
		this.validationService = validationService;
	}

	@KafkaListener(topics = accountTopic)
	public void consumeMessage(String message) throws JsonProcessingException {

		AccountValidateModel accountModel = objectMapper.readValue(message, AccountValidateModel.class);
		validationService.validate(accountModel);
	}

}
