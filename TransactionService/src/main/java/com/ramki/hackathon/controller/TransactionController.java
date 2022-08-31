package com.ramki.hackathon.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ramki.common.model.TransactionInfo;
import com.ramki.hackathon.model.RequestModel;
import com.ramki.hackathon.model.ResponseModel;
import com.ramki.hackathon.service.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/transaction")
@Api(tags = { "Transaction API" })
public class TransactionController {

	static final Logger log = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	TransactionService transactionService;

	@ResponseBody
	@ApiOperation(value = "Process a transaction request", response = String.class)
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<ResponseModel> addTransaction(@RequestBody RequestModel request) {
		log.info("Process transaction");
		ResponseModel response = transactionService.process(request);
		return new ResponseEntity<ResponseModel>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "List user transactions", response = String.class)
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<TransactionInfo>> getAllTransactions() {
		log.info("List user transactions");
		List<TransactionInfo> transactions = transactionService.getAllTransactions();
		System.out.println("Inside getAllUsers");
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	@ApiOperation(value = "List a transaction based on transaction id", response = String.class)
	@RequestMapping(value = "/{transactionId}", method = RequestMethod.GET)
	public ResponseEntity<TransactionInfo> getTransactions(@PathVariable long transactionId) {
		log.info("List a user transactions");
		TransactionInfo transaction = transactionService.getTransaction(transactionId);
		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}

}
