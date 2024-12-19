package com.example.memo.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "board")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    @Column(columnDefinition = "longtext")
    private String email;

    @ManyToOne
    @JoinColumn(name = "member=id")
    private Member member;

    public Board() {}

    public Board(String title, String contents, String email) {
        this.title = title;
        this.contents = contents;
        this.email = email;

    }

    public void setMember(Member member) {
        this.member = member;
    }
}

