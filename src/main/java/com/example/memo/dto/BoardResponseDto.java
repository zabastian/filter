package com.example.memo.dto;

import com.example.memo.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private final Long id;

    private final String title;

    private final String contents;

    private final String email;




    public BoardResponseDto(Long id, String title, String contents, String email) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.email = email;
    }

    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getContents(), board.getEmail());
    } //매개변수로 반환된 board가 Boardres
}
