package com.ramki.util;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;
import com.ramki.model.pacs008.Document;
import com.ramki.schema.producer.EventProducer;

@Component
public class SchemaValidatorUtil {

	@Autowired
	RestUtil restUtil;

//	@Autowired
//	EventProducer producer;
//
//	public boolean validateMT103(MT103 mtMessage) {
//		Boolean valid = true;
//		if (!valid)
//			producer.sendValidationError("MT103 validation failed ");
//		return valid;
//	}
//
//	public boolean validateMX(Document mxMessage) {
//		Boolean valid = true;
//		if (!valid)
//			producer.sendValidationError("MX validation failed");
//		return valid;
//	}

	public boolean validateMessage(long transactionID, String mtMessage, Document mxMessage) {

		Boolean validationFlag = true;

		try {
			File file = ResourceUtils.getFile("classpath:application.yml");
			String mtFilename = "MT_" + transactionID + ".txt";
			String mxFilename = "MX_" + transactionID + ".xml";
			String mtPath = file.getParent() + File.separator.concat(mtFilename);
			String mxPath = file.getParent() + File.separator.concat(mxFilename);
			Files.write(Paths.get(mtPath), mtMessage.getBytes());
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String jsonData = ow.writeValueAsString(mxMessage);
			String xmlData = convertToXML(jsonData);
			FileWriter xmlWritter = new FileWriter(mxPath);
			xmlWritter.write(xmlData);
			xmlWritter.flush();
			xmlWritter.close();

			validationFlag = restUtil.doPost(mtFilename, mxFilename);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return validationFlag;
	}

	private String convertToXML(String jsonString) {
		String xml = null;
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Document>" + XML.toString(jsonObject)
					+ "</Document>";
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return xml;
	}

}
