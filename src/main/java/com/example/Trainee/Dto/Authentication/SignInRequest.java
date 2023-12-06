package com.example.Trainee.Dto.Authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class SignInRequest {
    @NotEmpty(message = "fill in the field")
    private String email;
    @NotEmpty(message = "fill in the field")
    private String password;
}
