package com.example.Trainee.Dto.Authentication;

import com.example.Trainee.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String email;
    private Role role;
}
