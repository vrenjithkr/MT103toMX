package com.ramki.schema.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramki.model.TransactionEventModel;

@Component
public class EventProducer {
	@Value("${topic.transaction_topic}")
	private String transactionTopic;
	
	@Value("${topic.schema__validator_topic}")
	private String validatioTopic;

	private final ObjectMapper objectMapper;

	private final KafkaTemplate<String, JsonNode> kafkaTemplate;

	@Autowired
	public EventProducer(KafkaTemplate<String, JsonNode> kafkaTemplate, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}

	public String sendMessage(TransactionEventModel transaction) throws JsonProcessingException {
		JsonNode node = objectMapper.convertValue(transaction, JsonNode.class);
		kafkaTemplate.send(transactionTopic, node);
		return "message sent";
	}

	public String sendValidationError(String string) {
		JsonNode node = objectMapper.convertValue(string, JsonNode.class);
		kafkaTemplate.send(validatioTopic, node);
		return "message sent";
		
	}
}
