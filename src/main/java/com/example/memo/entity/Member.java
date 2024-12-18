package com.example.memo.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private Integer age;

    public Member() {
        this.password = password;
    }

    public Member(String username, String password, Integer age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}
