package com.zolve.wallet.exception;

import com.zolve.wallet.dto.ErrorWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidUserIdException.class)
    public ResponseEntity exceptionHandler(InvalidUserIdException e) {
        logger.info(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorWrapper("Invalid Request", e.getMessage()));
    }
    @ExceptionHandler(LowBalanceException.class)
    public ResponseEntity exceptionHandler(LowBalanceException e) {
        logger.info(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorWrapper("Invalid Request", e.getMessage()));
    }
    @ExceptionHandler(DuplicateUserNameException.class)
    public ResponseEntity exceptionHandler(DuplicateUserNameException e) {
        logger.info(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorWrapper("Invalid Request", e.getMessage()));
    }
    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity exceptionHandler(ObjectOptimisticLockingFailureException e) {
        logger.info(e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorWrapper("Encountered multiple transactions", "Another transaction in progress! Please try again later!!"));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception e) {
        logger.info(e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorWrapper("500", e.getMessage()));
    }
}