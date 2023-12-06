package com.example.Trainee.Service.impl;

import com.example.Trainee.Config.JwtService;
import com.example.Trainee.Dto.Authentication.AuthenticationResponse;
import com.example.Trainee.Dto.Authentication.SignInRequest;
import com.example.Trainee.Dto.Authentication.SignUpRequest;
import com.example.Trainee.Repo.UserRepo;
import com.example.Trainee.Service.AuthenticationService;
import com.example.Trainee.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse signUp(SignUpRequest request) {
        if(userRepo.existsByEmail(request.getEmail())){
            throw new RuntimeException(String.format("User with email: %s already exists",request.getEmail()));
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepo.save(user);

        String jwt = jwtService.generateToken(user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setEmail(user.getEmail());
        authenticationResponse.setRole(user.getRole());
        authenticationResponse.setToken(jwt);
        return authenticationResponse;
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest request) {
        User user = userRepo.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new NoSuchElementException(
                        "User with email: " + request.getEmail() + " not found"
                ));

        if (request.getEmail().isBlank()) {
            throw new BadCredentialsException("Email doesn't exist");
        }

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        String jwt = jwtService.generateToken(user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setEmail(user.getEmail());
        authenticationResponse.setRole(user.getRole());
        authenticationResponse.setToken(jwt);

        return authenticationResponse;
    }
}
