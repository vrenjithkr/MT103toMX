package com.ramki.schema.consumer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramki.model.TransactionEventModel;
import com.ramki.service.ValidationService;

@Component
public class Consumer {

	private static final String schemaTopic = "${topic.schema_topic}";

	private final ObjectMapper objectMapper;
	private final ModelMapper modelMapper;
	ValidationService validationService;

	@Autowired
	public Consumer(ObjectMapper objectMapper, ModelMapper modelMapper, ValidationService validationService) {
		this.objectMapper = objectMapper;
		this.modelMapper = modelMapper;
		this.validationService = validationService;
	}

	@KafkaListener(topics = schemaTopic)
	public void consumeMessage(String message) throws JsonProcessingException {

		TransactionEventModel transactionEventModel = objectMapper.readValue(message, TransactionEventModel.class);
		System.out.println(transactionEventModel.getMessage());
		validationService.validate(transactionEventModel);
	}

}
