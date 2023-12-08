package com.example.Trainee.Repo;

import com.example.Trainee.Dto.Trainee.GetTraineeProfile.GetTraineeProfileResponse;
import com.example.Trainee.entity.Trainee;
import com.example.Trainee.entity.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TraineeRepo extends JpaRepository<Trainee, Long> {

    @Query("SELECT t FROM Trainee t JOIN t.user u WHERE u.username = :username")
    Trainee findTraineeByUser_Username(@Param("username") String username);

    @Query("select t from Training t join t.trainee.user u where u.username in :usernames")
    List<Training> findByTraining_UserNameIn(@Param("usernames") List<String> usernames);

    @Query("SELECT new com.example.Trainee.Dto.Trainee.GetTraineeProfile.GetTraineeProfileResponse(t.user.firstName,t.user.lastName,t.date_of_birth,t.address,t.user.isActive) FROM Trainee t")
    Page<GetTraineeProfileResponse> getAllTrainee(Pageable pageable);

}