package com.example.Trainee.Service;

import com.example.Trainee.Dto.SimpleResponse;
import com.example.Trainee.Dto.Trainee.IsActive.ActiveDeActiveRequest;
import com.example.Trainee.Dto.Trainer.getTrainings.PeriodTrainingsList;
import com.example.Trainee.Dto.Trainer.getTrainings.ResponseTrainers;
import com.example.Trainee.Dto.TrainerResponse;
import com.example.Trainee.Dto.Trainer.GetTrainerProfile.GetTrainerProfileRequest;
import com.example.Trainee.Dto.Trainer.GetTrainerProfile.GetTrainerProfileResponse;
import com.example.Trainee.Dto.Trainer.UpdateTrainer.UpdateTrainerRequest;
import com.example.Trainee.Dto.Trainer.UpdateTrainer.UpdateTrainerResponse;
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
    UpdateTrainerResponse updateTrainerProfile(String username, UpdateTrainerRequest updateTrainerRequest);
    SimpleResponse activateTrainer(ActiveDeActiveRequest request);
    SimpleResponse deactivateTrainer(ActiveDeActiveRequest request);
    List<ResponseTrainers> getTrainings(PeriodTrainingsList periodTrainingsList);
}
