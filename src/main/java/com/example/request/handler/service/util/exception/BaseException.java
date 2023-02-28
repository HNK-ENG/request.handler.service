package com.example.request.handler.service.util.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseException extends Exception {
    private final String reason;
    private final HttpStatus httpStatus;
    private final String resultCode;
    private final StackTraceElement[] stackTraceElement;

    public BaseException(String message, String reason,HttpStatus httpStatus,String resultCode,StackTraceElement[] stackTraceElement) {
        super(message);
        this.stackTraceElement = stackTraceElement;
        this.reason = reason;
        this.httpStatus=httpStatus;
        this.resultCode=resultCode;
    }
}

