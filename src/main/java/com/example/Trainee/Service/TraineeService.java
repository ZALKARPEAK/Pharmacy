package com.example.Trainee.Service;


import com.example.Trainee.Dto.Trainee.GetTraineeProfile.GetTraineeProfileRequest;
import com.example.Trainee.Dto.Trainee.GetTraineeProfile.GetTraineeProfileResponse;
import com.example.Trainee.Dto.Trainee.IsActive.ActiveDeActiveRequest;
import com.example.Trainee.Dto.TrainerResponse;
import com.example.Trainee.Dto.Trainee.RegistrationTrainee.TraineeRequest;
import com.example.Trainee.Dto.SimpleResponse;
import com.example.Trainee.Dto.Trainee.UpdateTrainee.UpdateTraineeRequest;
import com.example.Trainee.Dto.Trainee.UpdateTrainee.UpdateTraineeResponse;
import com.example.Trainee.Dto.Trainee.UpdateTraineeTrainerList.UpdateTraineeTrainerListRequest;
import com.example.Trainee.Dto.TrainerInfo;
import com.example.Trainee.Dto.Training.getTraineeTrainingsList.GetTraineeTrainingsListRequest;
import com.example.Trainee.Dto.Training.getTraineeTrainingsList.TrainingResponse;
import com.example.Trainee.Dto.UserChangePasswordRequest;
import com.example.Trainee.Dto.UserCreateResponse;
import com.example.Trainee.Dto.UserCheckRequest;

import java.util.List;

public interface TraineeService {
    UserCreateResponse createTraineeProfile(TraineeRequest TraineeRequest);

    SimpleResponse checkCredentialsTrainee(UserCheckRequest userCheckRequest);

    SimpleResponse changeTraineePassword(UserChangePasswordRequest userChangePasswordRequest);

    UpdateTraineeResponse updateTraineeProfile(String username, UpdateTraineeRequest updateTraineeRequest);

    SimpleResponse deleteTraineeProfileByUsername(GetTraineeProfileRequest username);

    TrainerResponse updateTraineeTrainerList(UpdateTraineeTrainerListRequest request);

    List<TrainingResponse> getTraineeTrainingsList(GetTraineeTrainingsListRequest request);

    List<TrainerInfo> getNotAssignedActiveTrainersListForTrainee(GetTraineeProfileRequest request);

    GetTraineeProfileResponse SelectTraineeProfileByUsername(GetTraineeProfileRequest getTraineeProfileRequest);

    SimpleResponse activateTrainee(ActiveDeActiveRequest request);

    SimpleResponse deactivateTrainee(ActiveDeActiveRequest request);
}