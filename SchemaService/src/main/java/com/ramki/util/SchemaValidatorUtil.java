package com.ramki.util;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class SchemaValidatorUtil {

	@Autowired
	RestUtil restUtil;

	static final Logger log = LoggerFactory.getLogger(SchemaValidatorUtil.class);

	public boolean validateMessage(long transactionID, String mtMessage, String mxMessage) {

		Boolean validationFlag = true;

		try {
			File file = ResourceUtils.getFile("classpath:application.yml");
			String mtFilename = "MT_" + transactionID + ".txt";
			String mxFilename = "MX_" + transactionID + ".xml";
			String mtPath = file.getParent() + File.separator.concat(mtFilename);
			String mxPath = file.getParent() + File.separator.concat(mxFilename);
			Files.write(Paths.get(mtPath), mtMessage.getBytes());
			String xmlData = convertToXML(mxMessage);
			FileWriter xmlWritter = new FileWriter(mxPath);
			xmlWritter.write(xmlData);
			xmlWritter.flush();
			xmlWritter.close();

			validationFlag = restUtil.doPost(mtFilename, mxFilename);

		} catch (Exception e) {
			log.error("Error in SchemaValidatorUtil at method validateMessage. " + e.getMessage());
		}
		return validationFlag;
	}

	private String convertToXML(String mxMessage) {
		String xml = null;
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + mxMessage;
		return xml;
	}

}
