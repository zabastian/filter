package com.example.memo.globalexceptionhandler;

import org.springframework.http.HttpStatus;

public class ValidateException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public ValidateException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

//    public ValidateException(String message, HttpStatus httpStatus) {
//        super(message);
//  }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public String getMessage() {
        return this.message;
    }
}