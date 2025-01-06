package com.example.memo.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberid;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    private String kim;

//    public Member() {
//        this.password = password;
//    }

    public Member(String username, String password, String email, String kim) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.kim = kim;
    }

    public Member() {

    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }


}
