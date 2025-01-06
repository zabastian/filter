package com.example.memo.entity;

import com.example.memo.dto.CreateBoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "board")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardid;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    @Column(columnDefinition = "longtext")
    private String email;

    @Setter
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Board() {}

    public Board(String title, String contents, String email, Member member) {
        this.title = title;
        this.contents = contents;
        this.email = email;
        this.member = member;
    }

    public static Board of(CreateBoardRequestDto request, Member member) {
        return new Board(
                request.getTitle(),
                request.getContents(),
                request.getEmail(),
                member
        );
    }

}

