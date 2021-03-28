package com.zolve.wallet.exception;

public class NoTransactionsException extends Exception{
    public NoTransactionsException(){
        super("No transaction exists");
    }
}
