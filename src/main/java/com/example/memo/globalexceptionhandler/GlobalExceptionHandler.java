package com.example.memo.globalexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleCourseNotFoundException(MemberNotFoundException e) {
        ApiResponse<?> errorResponse = ApiResponse.error(HttpStatus.NOT_FOUND, "ERR404", e.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<ApiResponse<?>> handleValidateException(ValidateException e) {
        ApiResponse<?> errorResponse = ApiResponse.error(e.getHttpStatus(), "ERR400", e.getMessage());
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }
}



