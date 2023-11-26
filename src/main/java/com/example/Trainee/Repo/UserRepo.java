package com.example.Trainee.Repo;

import com.example.Trainee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
