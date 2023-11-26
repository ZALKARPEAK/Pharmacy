package com.example.Trainee.Repo;

import com.example.Trainee.entity.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TraineeRepo extends JpaRepository<Trainee, Long> {

    @Query("SELECT t FROM Trainee t JOIN FETCH t.user u WHERE u.username = :username")
    Trainee findTraineeByUser_Username(@Param("username") String username);
}
