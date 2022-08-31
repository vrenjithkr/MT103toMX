package com.ramki.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;
import com.ramki.model.TransactionEventModel;
import com.ramki.model.pacs008.Document;
import com.ramki.schema.producer.EventProducer;
import com.ramki.util.MTParser;
import com.ramki.util.MXConverter;
import com.ramki.util.SchemaValidatorUtil;

@Service
public class ValidationService {

	@Autowired
	MTParser parser;

	@Autowired
	MXConverter converter;

	@Autowired
	SchemaValidatorUtil validator;

	@Autowired
	EventProducer producer;
	
	static final Logger log = LoggerFactory.getLogger(ValidationService.class);

	public void validate(TransactionEventModel transactionEventModel) {

		MT103 mtMessage = parser.parseToMT103(transactionEventModel.getMessage());
//		if (validator.validateMT103(mtMessage)) {
			Document mxMessage = converter.convert(mtMessage);
			String mxText = converter.convert(mxMessage);
			if (validator.validateMessage(transactionEventModel.getTransactionId(), transactionEventModel.getMessage(),mxText)) {
				
				transactionEventModel.setSchemaValidation("success");
				transactionEventModel.setMxDocument(mxText);
				transactionEventModel
						.setTransactionAmount(mxMessage.getfIToFICstmrCdtTrf().getCdtTrfTxInf().getIntrBkSttlmAmt());
				transactionEventModel.setUserAccount(
						mxMessage.getfIToFICstmrCdtTrf().getCdtTrfTxInf().getDbtrAcct().getId().getOthr().getId());
			} else
				transactionEventModel.setSchemaValidation("failed");
			try {
				producer.sendMessage(transactionEventModel);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Error in ValidationService at method validate. "+e.getMessage());
			}
//		}

	}

}
