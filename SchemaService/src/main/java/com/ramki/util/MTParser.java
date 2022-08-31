package com.ramki.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;
import com.ramki.schema.producer.EventProducer;
import com.ramki.service.ValidationService;

@Component
public class MTParser {
	
	@Autowired
	EventProducer producer;
	
	static final Logger log = LoggerFactory.getLogger(MTParser.class);

	public MT103 parseToMT103(String message) {
		MT103 mtData = null;

		SwiftMessage sm;
		try {
			sm = SwiftMessage.parse(message);
			log.info("Info from method parseToMT103 in MTParser. Message type is "+sm.getType());
			if (sm.isServiceMessage()) {
				sm = SwiftMessage.parse(sm.getUnparsedTexts().getAsFINString());
			}
			if (sm.isType(103)) {
				mtData = new MT103(sm);
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Error in MTParser at method parseToMT103. "+e.getMessage());
			producer.sendValidationError("MT103 parse failed");
		}
		return mtData;
	}

}
