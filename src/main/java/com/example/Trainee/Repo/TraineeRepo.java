package com.example.Trainee.Repo;

import com.example.Trainee.entity.Trainee;
import com.example.Trainee.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TraineeRepo extends JpaRepository<Trainee, Long> {

    @Query("SELECT t FROM Trainee t JOIN t.user u WHERE u.username = :username")
    Trainee findTraineeByUser_Username(@Param("username") String username);

    @Query("SELECT t FROM Trainer t WHERE t.user.username=:username")
    Trainer findByTrainerName(@Param("username") String username);
}