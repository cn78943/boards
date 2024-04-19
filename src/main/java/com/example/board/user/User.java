package com.example.board.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String userId;

    public User(String userId) {
        this.userId = userId;
    }
}
