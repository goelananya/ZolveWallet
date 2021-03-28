package com.zolve.wallet.controller;

import com.zolve.wallet.bo.Transaction;
import com.zolve.wallet.bo.Wallet;
import com.zolve.wallet.exception.InvalidUserIdException;
import com.zolve.wallet.exception.LowBalanceException;
import com.zolve.wallet.exception.NoTransactionsException;
import com.zolve.wallet.service.TransactionService;
import com.zolve.wallet.util.ResponseWrapper;
import com.zolve.wallet.util.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("zolve")
public class WalletController {

    public TransactionService transactionService;

    @Autowired
    public WalletController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("balance")
    public ResponseEntity getBalance(@RequestParam Long userId) throws InvalidUserIdException {
        float balance = transactionService.getBalance(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Success", "Balance = " + balance));
    }

    @PostMapping("credit")
    public ResponseEntity credit(@RequestBody Transaction transaction) throws InvalidUserIdException, LowBalanceException {
        transaction.setTransactionType(TransactionType.Credit.name());
        transactionService.addTransaction(transaction);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Success", "Amount Credited"));
    }

    @PostMapping("debit")
    public ResponseEntity debit(@RequestBody Transaction transaction) throws InvalidUserIdException, LowBalanceException {
        transaction.setTransactionType(TransactionType.Debit.name());
        transactionService.addTransaction(transaction);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Success", "Amount Debited"));
    }

    @PostMapping("new/wallet")
    public ResponseEntity newWallet(@RequestBody Wallet wallet) {
        transactionService.createWallet(wallet);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Success", "Account Created"));
    }

    @GetMapping("transaction")
    public ResponseEntity getTransactions(@RequestParam Long userId) throws NoTransactionsException {
       List<Transaction> transactions =  transactionService.getTransactions(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Success", transactions));
    }
}