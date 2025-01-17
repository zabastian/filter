package com.example.memo.dto;

import lombok.Getter;

@Getter
public class CreateBoardRequestDto {

    private final String title;

    private final String contents;

    private final String username;

    private final String email;

    public CreateBoardRequestDto(String title, String contents, String username, String email) {
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.email = email;
    }

}