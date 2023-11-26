package com.example.Trainee.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateResponse {
    private String username;
    private String password;

    public UserCreateResponse(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
