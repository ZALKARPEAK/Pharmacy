package com.example.Trainee.Service.impl;

import com.example.Trainee.Dto.SimpleResponse;
import com.example.Trainee.Dto.Training.CreateTraining.AddTrainingRequest;
import com.example.Trainee.Repo.TraineeRepo;
import com.example.Trainee.Repo.TrainerRepo;
import com.example.Trainee.Repo.TrainingRepo;
import com.example.Trainee.Service.TrainingService;
import com.example.Trainee.entity.Trainee;
import com.example.Trainee.entity.Trainer;
import com.example.Trainee.entity.Training;
import com.example.Trainee.entity.Training_Types;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepo trainingRepo;
    private final TraineeRepo traineeRepo;
    private final TrainerRepo trainerRepo;

    @Override
    public ResponseEntity<SimpleResponse> addTraining(AddTrainingRequest addTrainingRequest) {
        Trainee trainee = traineeRepo.findTraineeByUser_Username(addTrainingRequest.getTraineeUsername());
        Trainer trainer = trainerRepo.findTrainerByUser_Username(addTrainingRequest.getTrainerUsername());

        if (trainee == null || trainer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Training training = new Training();
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingName(addTrainingRequest.getTrainingName());
        training.setTrainingDate(addTrainingRequest.getTrainingDate());
        training.setDuration(addTrainingRequest.getDuration());

        trainee.getTrainer().add(trainer);
        trainer.getTrainee().add(trainee);

        trainingRepo.save(training);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
