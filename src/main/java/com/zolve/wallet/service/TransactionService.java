package com.zolve.wallet.service;

import com.zolve.wallet.bo.Transaction;
import com.zolve.wallet.bo.Wallet;
import com.zolve.wallet.exception.InvalidUserIdException;
import com.zolve.wallet.exception.LowBalanceException;
import com.zolve.wallet.exception.NoTransactionsException;
import com.zolve.wallet.repo.TransactionRepo;
import com.zolve.wallet.repo.WalletRepo;
import com.zolve.wallet.util.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    public static TransactionRepo transactionRepo;
    public static WalletRepo walletRepo;
    public static int MIN_BALANCE;

    @Autowired
    public TransactionService(TransactionRepo transactionRepo, WalletRepo walletRepo) {
        this.transactionRepo = transactionRepo;
        this.walletRepo = walletRepo;
        MIN_BALANCE = Integer.parseInt(System.getenv("minBalance"));
    }


    @Transactional
    public void addTransaction(Transaction transaction) throws LowBalanceException, InvalidUserIdException {

        float updatedBalance;

        Optional<Wallet> walletOpt = walletRepo.findById(transaction.getUserId());
        if(walletOpt.isEmpty())    throw new InvalidUserIdException();

        Wallet wallet = walletOpt.get();

        if(transaction.getTransactionType().equals(TransactionType.Debit.name())) {
            updatedBalance = wallet.getBalance() - transaction.getTransactionAmount();
            if (updatedBalance < MIN_BALANCE) throw new LowBalanceException();
        } else {
            updatedBalance = wallet.getBalance() + transaction.getTransactionAmount();
        }

        wallet.setBalance(updatedBalance);

        walletRepo.save(wallet);
        transactionRepo.save(transaction);
    }

    public float getBalance(Long userId) throws InvalidUserIdException {
        Optional<Wallet> wallet = walletRepo.findById(userId);

        if(wallet.isEmpty())    throw new InvalidUserIdException();

        return wallet.get().getBalance();
    }

    public List<Transaction> getTransactions(Long userId) throws NoTransactionsException {
        List<Transaction> transactionList = transactionRepo.findAllByUserId(userId);
        if(transactionList.size()==0) throw new NoTransactionsException();
        return transactionList;
    }

    public void createWallet(Wallet wallet) {
        walletRepo.save(wallet);
    }
}