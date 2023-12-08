package com.example.Trainee.Dto.Authentication;

import com.example.Trainee.Enum.Role;
import com.example.Trainee.validator.Password;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpRequest {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Email
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

}