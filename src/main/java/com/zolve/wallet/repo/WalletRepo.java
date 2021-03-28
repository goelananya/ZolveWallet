package com.zolve.wallet.repo;

import com.zolve.wallet.bo.Wallet;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;

public interface WalletRepo extends CrudRepository<Wallet, String> {
    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    Wallet save(Wallet wallet);
}