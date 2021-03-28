package com.zolve.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorWrapper {
    String message;
    Object data;
}