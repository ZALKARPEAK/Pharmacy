package com.example.Trainee.Service;

import com.example.Trainee.Dto.SimpleResponse;
import com.example.Trainee.Dto.Training.CreateTraining.AddTrainingRequest;
import org.springframework.http.ResponseEntity;

public interface TrainingService {
    ResponseEntity<SimpleResponse> addTraining(AddTrainingRequest addTrainingRequest);
}
