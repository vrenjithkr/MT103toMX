package com.ramki.account.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramki.account.model.AccountModel;
import com.ramki.account.model.AccountValidateModel;
import com.ramki.account.producer.EventProducer;
import com.ramki.account.repository.AccountRepository;
import com.ramki.account.repository.TransactionsRepository;

@Service
public class ValidationService {

	@Autowired
	TransactionsRepository transactionRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	EventProducer producer;

	@PostConstruct
	void inserAccount() {
		accountRepository.save(new AccountModel("user123", 1000000, "ES0123456789012345671234"));
		accountRepository.save(new AccountModel("user980", 1000, "DX0123456789012345895623"));
		accountRepository.save(new AccountModel("user456", 10010, "KP0123456789012345678795"));
	}

	public void validate(AccountValidateModel transactionEventModel) {

		try {

			Optional<AccountModel> userAccount = accountRepository.findById(transactionEventModel.getUserID());
			if (userAccount.get().getAccountBalance() > transactionEventModel.getTransactionAmount()) {
				userAccount.get().setAccountBalance(
						userAccount.get().getAccountBalance() - transactionEventModel.getTransactionAmount());
				accountRepository.save(userAccount.get());
				transactionEventModel.setAccountValidation("success");
				producer.sendAccountMessage(transactionEventModel);
			} else {
				transactionEventModel.setAccountValidation("failed");
				producer.sendValidationError("Account validation failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
