package com.ramki.account.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramki.account.model.AccountValidateModel;

@Component
public class EventProducer {
	@Value("${topic.transaction_account_topic}")
	private String transactionTopic;
	
	@Value("${topic.account_validator_topic}")
	private String validatioTopic;

	private final ObjectMapper objectMapper;

	private final KafkaTemplate<String, JsonNode> kafkaTemplate;

	@Autowired
	public EventProducer(KafkaTemplate<String, JsonNode> kafkaTemplate, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}

	public String sendAccountMessage(AccountValidateModel account) throws JsonProcessingException {
		JsonNode node = objectMapper.convertValue(account, JsonNode.class);
		kafkaTemplate.send(transactionTopic, node);
		return "message sent";
	}

	public String sendValidationError(String string) {
		JsonNode node = objectMapper.convertValue(string, JsonNode.class);
		kafkaTemplate.send(validatioTopic, node);
		return "message sent";
		
	}
}
