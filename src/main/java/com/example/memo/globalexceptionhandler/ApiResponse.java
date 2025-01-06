package com.example.memo.globalexceptionhandler;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    // 속성
    private HttpStatus status;
    private String message;
    private String errorMessage;
    private T data;

    // 생성자
    private ApiResponse(HttpStatus status, String message, String errorMessage, T data) {
        this.status = status;
        this.message = message;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    // 기능
    //성공시
    public static <T> ApiResponse<T> success(HttpStatus status, String message, T data) {
        return new ApiResponse<>(status, message, null, data);
    }

    //실패시
    public static <T> ApiResponse<T> error(HttpStatus status, String message, String errorMessage) {
        return new ApiResponse<>(status, message, errorMessage, null);
    }

//    public static <T> ApiResponse<T> error(HttpStatus status, String message, T data) {
//        return new ApiResponse<>(status, message, data);
//    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}

