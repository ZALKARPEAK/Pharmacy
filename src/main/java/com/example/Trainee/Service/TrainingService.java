package com.example.Trainee.Service;

import com.example.Trainee.Dto.Trainee.RegistrationTrainee.TraineeResponse;
import com.example.Trainee.Dto.Trainee.GetTraineeProfile.TrainerResponse;
import com.example.Trainee.Dto.Training.TrainingResponse;
import org.springframework.http.ResponseEntity;

public interface TrainingService {
    TraineeResponse getTraineeTrainingsByCriteria(String traineeUsername, String criteria);
    ResponseEntity<String> addTraining(TrainingResponse trainingResponse, Long traineeId, Long trainerId, Long trainingTypesId);
    TrainerResponse getTrainerTrainingsByCriteria(String traineeUsername, String criteria);
}
