package com.example.memo.controller;

import com.example.memo.dto.LoginRequestDto;
import com.example.memo.globalexceptionhandler.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        // 로그인 로직
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Login successful", "session-key-example"));
    }
}

