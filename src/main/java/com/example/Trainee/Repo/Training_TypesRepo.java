package com.example.Trainee.Repo;


import com.example.Trainee.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Training_TypesRepo extends JpaRepository<Training, Long> {
}
