package com.example.Trainee.Service.impl;

import com.example.Trainee.Dto.Trainee.RegistrationTrainee.TraineeResponse;
import com.example.Trainee.Dto.TrainerResponse;
import com.example.Trainee.Dto.Training.TrainingResponse;
import com.example.Trainee.Repo.TrainingRepo;
import com.example.Trainee.Service.TrainingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepo trainingDao;

    @Override
    public TraineeResponse getTraineeTrainingsByCriteria(String traineeUsername, String criteria) {
        return null;
    }

    @Override
    public ResponseEntity<String> addTraining(TrainingResponse trainingResponse, Long traineeId, Long trainerId, Long trainingTypesId) {
        return null;
    }

    @Override
    public TrainerResponse getTrainerTrainingsByCriteria(String traineeUsername, String criteria) {
        return null;
    }
}
