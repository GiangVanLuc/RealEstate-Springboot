package com.javaweb.exception;

public class DuplicateCustomerException extends RuntimeException {
    public DuplicateCustomerException(String message) {
        super(message);
    }
}
