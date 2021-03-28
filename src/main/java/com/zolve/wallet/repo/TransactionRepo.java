package com.zolve.wallet.repo;

import com.zolve.wallet.bo.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepo extends CrudRepository<Transaction, String> {
    List<Transaction> findAllByUserName(String userName);
}