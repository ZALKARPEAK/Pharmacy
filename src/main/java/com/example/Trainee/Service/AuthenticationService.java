package com.example.Trainee.Service;

import com.example.Trainee.Dto.Authentication.AuthenticationResponse;
import com.example.Trainee.Dto.Authentication.SignInRequest;
import com.example.Trainee.Dto.Authentication.SignUpRequest;

public interface AuthenticationService {
    AuthenticationResponse signUp(SignUpRequest request);
    AuthenticationResponse signIn(SignInRequest request);
}
