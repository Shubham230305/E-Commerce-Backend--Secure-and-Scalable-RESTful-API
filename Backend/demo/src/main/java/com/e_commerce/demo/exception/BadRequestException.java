package com.e_commerce.demo.exception;

public class BadRequestException extends org.apache.coyote.BadRequestException {
    public BadRequestException(String message) {
        super(message);
    }
}
