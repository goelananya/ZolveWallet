package com.zolve.wallet.exception;

public class LowBalanceException extends Exception{
    public LowBalanceException() {
        super("Balance is Low!!");
    }
}
