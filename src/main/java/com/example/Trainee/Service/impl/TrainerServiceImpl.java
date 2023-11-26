package com.example.Trainee.Service.impl;

import com.example.Trainee.Dto.SimpleResponse;
import com.example.Trainee.Dto.Trainee.GetTraineeProfile.GetTraineeProfileResponse;
import com.example.Trainee.Dto.Trainee.GetTraineeProfile.TrainerResponse;
import com.example.Trainee.Dto.Trainer.GetTrainerProfile.GetTrainerProfileRequest;
import com.example.Trainee.Dto.Trainer.GetTrainerProfile.GetTrainerProfileResponse;
import com.example.Trainee.Dto.UserChangePasswordRequest;
import com.example.Trainee.Dto.UserCheckRequest;
import com.example.Trainee.Dto.Trainer.RegistrationTrainer.TrainerRequest;
import com.example.Trainee.Dto.UserCreateResponse;
import com.example.Trainee.Repo.TrainerRepo;
import com.example.Trainee.Repo.Training_TypesRepo;
import com.example.Trainee.Repo.UserRepo;
import com.example.Trainee.Service.TrainerService;
import com.example.Trainee.Service.UserService;

import com.example.Trainee.entity.Trainer;
import com.example.Trainee.entity.Training_Types;
import com.example.Trainee.entity.User;
import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepo trainerDao;
    private final UserService userService;
    private final UserRepo userRepo;
    private final Training_TypesRepo trainingTypesRepo;

    @Override
    public UserCreateResponse createTrainerProfile(TrainerRequest trainerRequest) {
        User user = new User();
        user.setFirstName(trainerRequest.getFirstName());
        user.setLastName(trainerRequest.getLastName());

        user.setUsername(userService.generateUsername(user.getFirstName(), user.getLastName()));
        user.setPassword(userService.generatePassword());
        userRepo.save(user);

        Training_Types trainingTypes = Training_Types.builder().TrainingTypeName(trainerRequest.getSpecialization()).build();

        Trainer trainer = Trainer.builder()
                .trainingTypes(trainingTypes)
                .user(user)
                .build();

        trainerDao.save(trainer);

        return new UserCreateResponse(
                user.getUsername(),
                user.getPassword());
    }

    @Override
    public SimpleResponse checkCredentialsTrainer(UserCheckRequest userCheckRequest) {
        Trainer trainer = trainerDao.findTrainerByUser_Username(userCheckRequest.getUsername());
        if (trainer != null) {
            User user = trainer.getUser();
            if (user.getPassword().equals(userCheckRequest.getPassword())) {
                return SimpleResponse.builder()
                        .status(HttpStatus.OK)
                        .massage("Username and Password correct!")
                        .build();
            } else {
                return SimpleResponse.builder()
                        .status(HttpStatus.UNAUTHORIZED)
                        .massage("Incorrect password")
                        .build();
            }
        } else {
            return SimpleResponse.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .massage("Incorrect username")
                    .build();
        }
    }

    @Override
    public GetTrainerProfileResponse SelectTrainerProfileByUsername(GetTrainerProfileRequest request) {
        Trainer trainer = trainerDao.findTrainerByUser_Username(request.getUsername());

        if(trainer == null){
            return null;
        }

        User user = trainer.getUser();
        GetTrainerProfileResponse response = new GetTrainerProfileResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setActive(user.isActive());
        response.setTrainingTypes(user.getTrainer().getTrainingTypes());
        return response;
    }

    @Override
    public SimpleResponse changeTrainerPassword(UserChangePasswordRequest userChangePasswordRequest) {
        try {
            Trainer trainee = trainerDao.findTrainerByUser_Username(userChangePasswordRequest.getUsername());

            if (trainee != null && trainee.getUser().getPassword().equals(userChangePasswordRequest.getOldPassword())) {
                User user = trainee.getUser();
                user.setPassword(userChangePasswordRequest.getNewPassword());
                userRepo.save(user);
                return SimpleResponse.builder().status(HttpStatus.OK).massage("Change successful").build();
            } else {
                return SimpleResponse.builder().status(HttpStatus.UNAUTHORIZED).massage("Invalid username or password").build();
            }
        } catch (NoResultException e) {
            return SimpleResponse.builder().status(HttpStatus.UNAUTHORIZED).massage("User not found").build();
        }
    }

    @Override
    public TrainerResponse updateTrainerProfile(TrainerResponse trainerResponse) {
        return null;
    }

    @Override
    public ResponseEntity<String> activateTrainer(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> deactivateTrainer(Long id) {
        return null;
    }

    @Override
    public TrainerResponse updateTrainerTrainingList(String trainerUsername, List<Long> trainerIds) {
        return null;
    }

    @Override
    public TrainerResponse getTrainerTrainingList(String trainerUsername) {
        return null;
    }
}
