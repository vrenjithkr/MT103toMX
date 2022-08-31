package com.ramki.hackathon.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramki.hackathon.model.AccountValidateModel;
import com.ramki.hackathon.model.TransactionEventModel;

@Component
public class EventProducer {
	@Value("${topic.schema_topic}")
	private String schemaTopic;
	
	@Value("${topic.account_topic}")
	private String accountTopic;

	private final ObjectMapper objectMapper;

	private final KafkaTemplate<String, JsonNode> kafkaTemplate;

	@Autowired
	public EventProducer(KafkaTemplate<String, JsonNode> kafkaTemplate, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}

	public String sendMessage(TransactionEventModel transaction) throws JsonProcessingException {
		JsonNode node = objectMapper.convertValue(transaction, JsonNode.class);
		kafkaTemplate.send(schemaTopic, node);
		return "message sent";
	}
	
	public String sendAccountMessage(AccountValidateModel account) throws JsonProcessingException {
		JsonNode node = objectMapper.convertValue(account, JsonNode.class);
		kafkaTemplate.send(accountTopic, node);
		return "message sent";
	}
}
