package com.example.Trainee.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserChangePasswordRequest {
    private String username;
    private String oldPassword;
    private String newPassword;
}
