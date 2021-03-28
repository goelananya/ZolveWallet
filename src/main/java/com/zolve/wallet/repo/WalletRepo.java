package com.zolve.wallet.repo;

import com.zolve.wallet.bo.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepo extends CrudRepository<Wallet, Long> {
    Wallet findByUserId(Long userId);
}