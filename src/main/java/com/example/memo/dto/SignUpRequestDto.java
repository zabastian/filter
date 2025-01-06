package com.example.memo.dto;

import lombok.Getter;

@Getter
public class SignUpRequestDto {
    private final String username;

    private final String password;

    private final String email;

    private final String kim;


    public SignUpRequestDto(String username, String password, String email, String kim) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.kim = kim;
    }
}
