package com.ramki.hackathon.utils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ramki.hackathon.model.TransactionEventModel;
import com.ramki.hackathon.model.TransactionsModel;
import com.ramki.hackathon.producer.EventProducer;
import com.ramki.hackathon.repository.TransactionsRepository;

@Component
public class FileUploadUtil {

	@Autowired
	TransactionsRepository transactionRepository;

	@Autowired
	EventProducer producer;

	static final Logger log = LoggerFactory.getLogger(FileUploadUtil.class);

	public long saveFile(String userId, String fileName, MultipartFile multipartFile) {

		try (InputStream inputStream = multipartFile.getInputStream()) {
			File file = ResourceUtils.getFile("classpath:application.yaml");
			Path uploadPath = Paths.get(file.getParent());
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

			String data = "";
			data = new String(Files.readAllBytes(Paths.get(filePath.toString())));
			TransactionsModel transaction = new TransactionsModel(userId, data);
			TransactionsModel transactions = transactionRepository.save(transaction);
			TransactionEventModel transactionEventModel = new TransactionEventModel(transactions.getTransactionId(),
					userId, data);
			try {
				producer.sendMessage(transactionEventModel);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				log.error("Error in TransactionService at method process. " + e.getMessage());
			}
			return transactions.getTransactionId();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

}