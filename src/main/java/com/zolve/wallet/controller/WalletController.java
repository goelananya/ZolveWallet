package com.zolve.wallet.controller;

import com.zolve.wallet.bo.Transaction;
import com.zolve.wallet.bo.Wallet;
import com.zolve.wallet.dto.BalanceResponse;
import com.zolve.wallet.exception.DuplicateUserNameException;
import com.zolve.wallet.exception.InvalidUserIdException;
import com.zolve.wallet.exception.LowBalanceException;
import com.zolve.wallet.exception.NoTransactionsException;
import com.zolve.wallet.service.TransactionService;
import com.zolve.wallet.service.WalletService;
import com.zolve.wallet.dto.ErrorWrapper;
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
    public WalletService walletService;

    @Autowired
    public WalletController(TransactionService transactionService, WalletService walletService) {
        this.transactionService = transactionService;
        this.walletService = walletService;
    }

    @GetMapping("balance")
    public ResponseEntity getBalance(@RequestParam String userName) throws InvalidUserIdException {
        float balance = walletService.getBalance(userName);
        return ResponseEntity.ok(new BalanceResponse(balance));
    }

    @PostMapping("credit")
    public ResponseEntity credit(@RequestBody Transaction transaction) throws InvalidUserIdException, LowBalanceException, InterruptedException {
        transaction.setTransactionType(TransactionType.Credit.name());
        transactionService.addTransaction(transaction);
        return ResponseEntity.ok().build();
    }

    @PostMapping("debit")
    public ResponseEntity debit(@RequestBody Transaction transaction) throws InvalidUserIdException, LowBalanceException, InterruptedException {
        transaction.setTransactionType(TransactionType.Debit.name());
        transactionService.addTransaction(transaction);
        return ResponseEntity.ok().build();
    }

    @PostMapping("new/wallet")
    public ResponseEntity newWallet(@RequestBody Wallet wallet) throws DuplicateUserNameException, LowBalanceException {
        walletService.createWallet(wallet);
        return ResponseEntity.ok().build();
    }

    @GetMapping("transaction")
    public ResponseEntity getTransactions(@RequestParam String userName) throws NoTransactionsException {
       List<Transaction> transactions =  transactionService.getTransactions(userName);
        return ResponseEntity.ok(transactions);
    }
}