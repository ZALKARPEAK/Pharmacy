package com.example.Trainee.Dto.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;
}
