package com.example.memo.dto;

import lombok.Getter;

@Getter
public class BoardWithAgeResponseDto {
    private final String title;
    private final String contents;
    private final String email;

    public BoardWithAgeResponseDto(String title, String contents, String email) {
        this.title = title;
        this.contents = contents;
        this.email = email;
    }
}
