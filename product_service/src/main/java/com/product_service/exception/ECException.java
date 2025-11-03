package com.product_service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ECException extends Exception {
    private Integer statusCode;
    private String errorCode;
    private String message;
}
