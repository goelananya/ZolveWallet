package com.zolve.wallet.util;

import org.springframework.beans.factory.annotation.Value;

public enum TransactionType {

    @Value("Debit")
    Debit,

    @Value("Credit")
    Credit
}