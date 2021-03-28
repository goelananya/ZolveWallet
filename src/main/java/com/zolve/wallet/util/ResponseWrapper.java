package com.zolve.wallet.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseWrapper{
    String message;
    Object data;
}
