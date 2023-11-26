package com.example.Trainee.Repo;

import com.example.Trainee.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrainerRepo extends JpaRepository<Trainer, Long> {
    @Query("SELECT t FROM Trainer t JOIN FETCH t.user u WHERE u.username = :username")
    Trainer findTrainerByUser_Username(@Param("username") String username);
}
