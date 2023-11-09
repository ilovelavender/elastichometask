package com.olegrubin.vicariusdemo.controller.exception;

import com.olegrubin.vicariusdemo.model.InternalException;
import com.olegrubin.vicariusdemo.model.common.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<ErrorResponse> handle(final InternalException exe) {
        Logger logger = getLogger(exe);
        logger.error(exe.getMessage(), exe);
        return new ResponseEntity<>(
            new ErrorResponse(exe.getMessage()), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handle(final HttpMessageNotReadableException exe) {
        return new ResponseEntity<>(
            new ErrorResponse(exe.getMessage()), BAD_GATEWAY);
    }

    private static Logger getLogger(final Exception exe) {
        if (exe != null && exe.getStackTrace() != null && exe.getStackTrace().length > 0) {
            return LoggerFactory.getLogger(exe.getStackTrace()[0].getClassName());
        }
        return LoggerFactory.getLogger(GlobalExceptionHandler.class);
    }
}
