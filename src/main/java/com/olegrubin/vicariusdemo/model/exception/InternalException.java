package com.olegrubin.vicariusdemo.model.exception;

public class InternalException extends RuntimeException {

    public InternalException(String message, Exception cause) {
        super(message, cause);
    }
}
