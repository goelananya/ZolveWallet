package com.zolve.wallet.exception;

public class DuplicateUserNameException extends Exception {
    public DuplicateUserNameException() {
        super("User name exists! Please try again with different username");
    }
}
