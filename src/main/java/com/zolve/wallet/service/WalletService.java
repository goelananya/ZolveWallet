package com.zolve.wallet.service;

import com.zolve.wallet.bo.Wallet;
import com.zolve.wallet.exception.DuplicateUserNameException;
import com.zolve.wallet.exception.InvalidUserIdException;
import com.zolve.wallet.exception.LowBalanceException;
import com.zolve.wallet.repo.WalletRepo;
import com.zolve.wallet.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService {

    public static WalletRepo walletRepo;
    public static int MIN_BALANCE;

    @Autowired
    public WalletService(WalletRepo walletRepo) {
        this.walletRepo = walletRepo;
        if(System.getenv("minBalance")==null) {
            MIN_BALANCE = Constants.defaultMinBalance;
        } else {
            MIN_BALANCE = Integer.parseInt(System.getenv("minBalance"));
        }
    }

    public void createWallet(Wallet wallet) throws DuplicateUserNameException, LowBalanceException {
        Optional<Wallet> walletOpt = walletRepo.findById(wallet.getUserName());
        if(!walletOpt.isEmpty())    throw new DuplicateUserNameException();
        if(wallet.getBalance()<MIN_BALANCE) throw new LowBalanceException();
        walletRepo.save(wallet);
    }

    public float getBalance(String userName) throws InvalidUserIdException {
        Optional<Wallet> wallet = walletRepo.findById(userName);

        if(wallet.isEmpty())    throw new InvalidUserIdException();

        return wallet.get().getBalance();
    }

}
