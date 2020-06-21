package com.tw.exception;

public class ProductException extends Exception {
    public enum ExceptionType {
        OUT_OF_STOCK,
        INVALID_ORDER,
        ALREADY_PLACED,
        ORDER_NOT_PRESENT,
        MORE_THAN_QUANTITY;
    }

    public ExceptionType type;

    public ProductException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
