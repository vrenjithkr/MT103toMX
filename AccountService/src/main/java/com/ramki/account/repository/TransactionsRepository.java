package com.ramki.account.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ramki.account.model.TransactionsModel;

@Repository
public interface TransactionsRepository extends CrudRepository<TransactionsModel, Long> {

}
