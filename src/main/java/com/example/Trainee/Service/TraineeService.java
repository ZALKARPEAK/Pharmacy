package com.example.Trainee.Service;


import com.example.Trainee.Dto.Trainee.GetTraineeProfile.GetTraineeProfileRequest;
import com.example.Trainee.Dto.Trainee.GetTraineeProfile.GetTraineeProfileResponse;
import com.example.Trainee.Dto.Trainee.RegistrationTrainee.TraineeRequest;
import com.example.Trainee.Dto.SimpleResponse;
import com.example.Trainee.Dto.Trainee.RegistrationTrainee.TraineeResponse;
import com.example.Trainee.Dto.Trainee.UpdateTrainee.UpdateTraineeRequest;
import com.example.Trainee.Dto.Trainee.UpdateTrainee.UpdateTraineeResponse;
import com.example.Trainee.Dto.UserChangePasswordRequest;
import com.example.Trainee.Dto.UserCreateResponse;
import com.example.Trainee.Dto.UserCheckRequest;

import java.util.List;

public interface TraineeService {
    UserCreateResponse createTraineeProfile(TraineeRequest TraineeRequest);

    SimpleResponse checkCredentialsTrainee(UserCheckRequest userCheckRequest);

    SimpleResponse changeTraineePassword(UserChangePasswordRequest userChangePasswordRequest);

    UpdateTraineeResponse updateTraineeProfile(String username, UpdateTraineeRequest updateTraineeRequest);

    TraineeResponse activateTrainee(Long id);

    TraineeResponse deactivateTrainee(Long id);

    SimpleResponse deleteTraineeProfileByUsername(GetTraineeProfileRequest username);

    TraineeResponse updateTraineeTrainingList(String traineeUsername, List<Long> trainerIds);

    TraineeResponse getTraineeTrainingList(String traineeUsername);

    List<TraineeResponse> getNotAssignedActiveTrainersListForTrainee(String trainerUsername);

    GetTraineeProfileResponse SelectTraineeProfileByUsername(GetTraineeProfileRequest getTraineeProfileRequest);
}