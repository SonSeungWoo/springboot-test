package com.ssw.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long userId;

    @Column(name = "user_name", unique = true, length = 64)
    private String userName;

    @Column(name = "password", length = 256)
    private String password;

    @Column(name = "email", length = 128)
    private String email;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
