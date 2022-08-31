package com.ramki.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;
import com.ramki.schema.producer.EventProducer;

@Component
public class MTParser {
	
	@Autowired
	EventProducer producer;

	public MT103 parseToMT103(String message) {
		MT103 mtData = null;

		SwiftMessage sm;
		try {
			sm = SwiftMessage.parse(message);
			if (sm.isServiceMessage()) {
				sm = SwiftMessage.parse(sm.getUnparsedTexts().getAsFINString());
			}
			System.out.println("Message Type: " + sm.getType());
			if (sm.isType(103)) {
				mtData = new MT103(sm);
				System.out.println("Reference: " + mtData.getField20().getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
			producer.sendValidationError("MT103 parse failed");
		}
		return mtData;
	}

}
