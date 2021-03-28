package com.zolve.wallet.exception;

public class InvalidUserIdException extends Exception {
    public InvalidUserIdException() {
        super("Invalid userid received");
    }
}
