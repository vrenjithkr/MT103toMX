package com.ramki.hackathon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ramki.hackathon.model.TransactionsModel;

@Repository
public interface TransactionsRepository extends CrudRepository<TransactionsModel, Long> {

}
