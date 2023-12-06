package com.example.Trainee.Repo;

import com.example.Trainee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findUserByEmail(String email);
}
