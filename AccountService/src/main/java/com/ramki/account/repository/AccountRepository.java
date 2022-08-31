package com.ramki.account.repository;

import org.springframework.data.repository.CrudRepository;

import com.ramki.account.model.AccountModel;

public interface AccountRepository extends CrudRepository<AccountModel, String> {

}
