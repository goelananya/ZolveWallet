package com.zolve.wallet.exception;

import com.zolve.wallet.util.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidUserIdException.class)
    public ResponseEntity exceptionHandler(InvalidUserIdException e) {
        logger.info(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Invalid Request", e.getMessage()));
    }
    @ExceptionHandler(LowBalanceException.class)
    public ResponseEntity exceptionHandler(LowBalanceException e) {
        logger.info(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Invalid Request", e.getMessage()));
    }
    @ExceptionHandler(NoTransactionsException.class)
    public ResponseEntity exceptionHandler(NoTransactionsException e) {
        logger.info(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Invalid Request", e.getMessage()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception e) {
        logger.info(e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper("500", e.getMessage()));
    }
}