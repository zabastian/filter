package com.example.memo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDto {
    @NotEmpty(message = "Username need")
    private String username;
    @NotEmpty(message = "Password need")
    private String password;
}

