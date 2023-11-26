package com.example.Trainee.Service;

import com.example.Trainee.Dto.SimpleResponse;
import com.example.Trainee.Dto.Trainee.GetTraineeProfile.TrainerResponse;
import com.example.Trainee.Dto.Trainer.GetTrainerProfile.GetTrainerProfileRequest;
import com.example.Trainee.Dto.Trainer.GetTrainerProfile.GetTrainerProfileResponse;
import com.example.Trainee.Dto.UserChangePasswordRequest;
import com.example.Trainee.Dto.UserCheckRequest;
import com.example.Trainee.Dto.Trainer.RegistrationTrainer.TrainerRequest;
import com.example.Trainee.Dto.UserCreateResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TrainerService {
    UserCreateResponse createTrainerProfile(TrainerRequest trainerRequest);
    SimpleResponse checkCredentialsTrainer(UserCheckRequest userCheckRequest);
    GetTrainerProfileResponse SelectTrainerProfileByUsername(GetTrainerProfileRequest request);
    SimpleResponse changeTrainerPassword(UserChangePasswordRequest userChangePasswordRequest);
    TrainerResponse updateTrainerProfile(TrainerResponse trainerResponse);
    ResponseEntity<String> activateTrainer(Long id);
    ResponseEntity<String> deactivateTrainer(Long id);
    TrainerResponse updateTrainerTrainingList(String trainerUsername, List<Long> trainerIds);
    TrainerResponse getTrainerTrainingList(String trainerUsername);
}
