package com.example.Trainee.Service.impl;

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
import com.example.Trainee.Repo.TraineeRepo;
import com.example.Trainee.Repo.TrainerRepo;
import com.example.Trainee.Repo.UserRepo;
import com.example.Trainee.Service.TrainerService;
import com.example.Trainee.Service.UserService;

import com.example.Trainee.entity.*;
import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepo trainerDao;
    private final UserService userService;
    private final UserRepo userRepo;
    private final TraineeRepo traineeRepo;

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

        user.setTrainer(trainer);
        trainer.setUser(user);
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

        GetTrainerProfileResponse response = new GetTrainerProfileResponse();
        response.setFirstName(trainer.getUser().getFirstName());
        response.setLastName(trainer.getUser().getLastName());
        response.setActive(trainer.getUser().isActive());
        response.setTrainingTypes(trainer.getUser().getTrainer().getTrainingTypes());
        response.setTraineeResponses(trainer.getTrainee());
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
    public UpdateTrainerResponse updateTrainerProfile(String username, UpdateTrainerRequest updateTrainerRequest) {
        Trainer trainer = trainerDao.findTrainerByUser_Username(username);

        if(trainer == null){
            return null;
        }

        User user = trainer.getUser();
        user.setUsername(updateTrainerRequest.getUsername());
        user.setFirstName(updateTrainerRequest.getFirstName());
        user.setLastName(updateTrainerRequest.getLastName());
        user.setActive(updateTrainerRequest.isActive());
        userRepo.save(user);

        UpdateTrainerResponse updateTraineeResponse = new UpdateTrainerResponse();
        updateTraineeResponse.setUsername(updateTrainerRequest.getUsername());
        updateTraineeResponse.setFirstName(updateTrainerRequest.getFirstName());
        updateTraineeResponse.setLastName(updateTrainerRequest.getLastName());
        updateTraineeResponse.setTrainingTypes(updateTrainerRequest.getTrainingTypes());
        updateTraineeResponse.setActive(updateTrainerRequest.isActive());
        updateTraineeResponse.setTraineeResponse(trainer.getTrainee());

        return updateTraineeResponse;
    }

    @Override
    public SimpleResponse activateTrainer(ActiveDeActiveRequest request) {
        Trainer trainer = trainerDao.findTrainerByUser_Username(request.getUsername());

        if (trainer != null && !trainer.getUser().isActive()) {
            trainer.getUser().setActive(true);
            return SimpleResponse.builder().massage("Activated").status(HttpStatus.OK).build();
        }

        return SimpleResponse.builder().massage("Trainer not found or already active").status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public SimpleResponse deactivateTrainer(ActiveDeActiveRequest request) {
        Trainer trainer = trainerDao.findTrainerByUser_Username(request.getUsername());

        if (trainer != null && trainer.getUser().isActive()) {
            trainer.getUser().setActive(false);
            return SimpleResponse.builder().massage("Deactivated").status(HttpStatus.OK).build();
        }

        return SimpleResponse.builder().massage("Trainer not found or already inactive").status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public List<ResponseTrainers> getTrainings(PeriodTrainingsList periodTrainingsList) {
        Trainee trainee = traineeRepo.findTraineeByUser_Username(periodTrainingsList.getUserName());

        List<String> trainings1 = Collections.singletonList(periodTrainingsList.getUserName());
        List<Training> trainings = traineeRepo.findByTraining_UserNameIn(trainings1);

        LocalDate periodFrom = periodTrainingsList.getPeriodFrom();
        LocalDate periodTo = periodTrainingsList.getPeriodTo();

        long durationInDays = periodTo.toEpochDay() - periodFrom.toEpochDay();

        return trainings.stream()
                .map(training -> new ResponseTrainers(
                        training.getTrainingName(),
                        training.getTrainingDate(),
                        (int) durationInDays,
                        training.getTrainer().getUser().getFirstName()
                ))
                .collect(Collectors.toList());
    }
}
