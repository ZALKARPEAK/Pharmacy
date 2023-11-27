package com.example.Trainee.Service.impl;


import com.example.Trainee.Dto.SimpleResponse;
import com.example.Trainee.Dto.Trainee.GetTraineeProfile.GetTraineeProfileRequest;
import com.example.Trainee.Dto.Trainee.GetTraineeProfile.GetTraineeProfileResponse;
import com.example.Trainee.Dto.TrainerResponse;
import com.example.Trainee.Dto.Trainee.RegistrationTrainee.TraineeRequest;
import com.example.Trainee.Dto.Trainee.RegistrationTrainee.TraineeResponse;
import com.example.Trainee.Dto.Trainee.UpdateTrainee.UpdateTraineeRequest;
import com.example.Trainee.Dto.Trainee.UpdateTrainee.UpdateTraineeResponse;
import com.example.Trainee.Dto.Trainee.UpdateTraineeTrainerList.UpdateTraineeTrainerListRequest;
import com.example.Trainee.Dto.TrainerInfo;
import com.example.Trainee.Dto.UserChangePasswordRequest;
import com.example.Trainee.Dto.UserCreateResponse;
import com.example.Trainee.Dto.UserCheckRequest;
import com.example.Trainee.Repo.TraineeRepo;
import com.example.Trainee.Repo.TrainerRepo;
import com.example.Trainee.Repo.UserRepo;
import com.example.Trainee.Service.TraineeService;
import com.example.Trainee.Service.UserService;
import com.example.Trainee.entity.Trainee;
import com.example.Trainee.entity.Trainer;
import com.example.Trainee.entity.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class TraineeServiceImpl implements TraineeService {

    private final TraineeRepo traineeRepo;
    private final TrainerRepo trainerRepo;
    private final UserService userService;
    private final UserRepo userRepo;

    @Override
    public UserCreateResponse createTraineeProfile(TraineeRequest traineeRequest) {
        User user = new User();
        user.setFirstName(traineeRequest.getFirst_Name());
        user.setLastName(traineeRequest.getLast_Name());

        user.setUsername(userService.generateUsername(user.getFirstName(), user.getLastName()));
        user.setPassword(userService.generatePassword());

        Trainee trainee = Trainee.builder()
                .address(traineeRequest.getAddress())
                .date_of_birth(traineeRequest.getDate_of_birth())
                .user(user)
                .build();

        user.setTrainee(trainee);
        trainee.setUser(user);
        traineeRepo.save(trainee);
        return new UserCreateResponse(
                user.getUsername(),
                user.getPassword());
    }

    @Override
    public SimpleResponse checkCredentialsTrainee(UserCheckRequest userCheckRequest) {
        Trainee trainee = traineeRepo.findTraineeByUser_Username(userCheckRequest.getUsername());
        if (trainee != null) {
            User user = trainee.getUser();
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
    public SimpleResponse changeTraineePassword(UserChangePasswordRequest userChangePasswordRequest) {
        try {
            Trainee trainee = traineeRepo.findTraineeByUser_Username(userChangePasswordRequest.getUsername());

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
    public GetTraineeProfileResponse SelectTraineeProfileByUsername(GetTraineeProfileRequest request) {
        Trainee trainee = traineeRepo.findTraineeByUser_Username(request.getUsername());
        if (trainee == null) {
            return null;
        }

        GetTraineeProfileResponse response = new GetTraineeProfileResponse();
        response.setFirstName(trainee.getUser().getFirstName());
        response.setLastName(trainee.getUser().getLastName());
        response.setDateOfBirth(trainee.getDate_of_birth());
        response.setAddress(trainee.getAddress());
        response.setTrainerResponses(trainee.getTrainer());
        return response;
    }

    @Override
    public UpdateTraineeResponse updateTraineeProfile(String username, UpdateTraineeRequest updateTraineeRequest)  {
        Trainee trainee = traineeRepo.findTraineeByUser_Username(username);

        if(trainee == null){
            return null;
        }

        User user = trainee.getUser();
        user.setUsername(updateTraineeRequest.getUsername());
        user.setFirstName(updateTraineeRequest.getFirstName());
        user.setLastName(updateTraineeRequest.getLastName());
        user.setActive(updateTraineeRequest.isActive());
        userRepo.save(user);

        trainee.setAddress(updateTraineeRequest.getAddress());
        trainee.setDate_of_birth(updateTraineeRequest.getDateOfBirth());
        traineeRepo.save(trainee);

        UpdateTraineeResponse updateTraineeResponse = new UpdateTraineeResponse();
        updateTraineeResponse.setUsername(updateTraineeRequest.getUsername());
        updateTraineeResponse.setFirstName(updateTraineeRequest.getFirstName());
        updateTraineeResponse.setLastName(updateTraineeRequest.getLastName());
        updateTraineeResponse.setDateOfBirth(updateTraineeRequest.getDateOfBirth());
        updateTraineeResponse.setAddress(updateTraineeRequest.getAddress());
        updateTraineeResponse.setActive(updateTraineeRequest.isActive());
        updateTraineeResponse.setTraineeResponse(trainee.getTrainer());

        return updateTraineeResponse;
    }

    @Override
    public SimpleResponse deleteTraineeProfileByUsername(GetTraineeProfileRequest username) {
        Trainee trainee=traineeRepo.findTraineeByUser_Username(username.getUsername());
        User user=trainee.getUser();
        trainee.setUser(null);
        userRepo.delete(user);

        return SimpleResponse.builder().status(HttpStatus.OK).massage("User not found").build();
    }

    @Override
    public List<TrainerInfo> getNotAssignedActiveTrainersListForTrainee(GetTraineeProfileRequest request) {
        /*Trainee trainee = traineeRepo.findTraineeByUser_Username(request.getUsername());

        if (trainee == null) {
            return new ArrayList<>();
        }

        List<Trainer> trainers = trainee.getTrainer();
        List<Trainer> active = trainerRepo.findActiveTrainer();

        List<Trainer> notAssigned = trainers.stream().filter(
                trainer -> !active.contains(trainer)
        ).toList();

        return notAssigned.stream().map(
                trainer -> {
                    TrainerInfo response = new TrainerInfo();
                    response.setUserName(trainer.getUser().getUsername());
                    response.setFirstName(trainer.getUser().getFirstName());
                    response.setLastName(trainer.getUser().getLastName());
                    response.setTrainingTypes(trainer.getTrainingTypes());
                    return response;
                }
        ).collect(Collectors.toList());*/

        String traineeUsername = request.getUsername();
        Trainee trainee = traineeRepo.findTraineeByUser_Username(traineeUsername);
        List<Trainer> trainers = trainerRepo.findActiveTrainer();


        List<Trainer> assignedTrainers = trainee.getTrainer();

        List<Trainer> notAssigned = trainers.stream()
                .filter(trainer -> !assignedTrainers.contains(trainer))
                .toList();

        return notAssigned.stream().map(
                trainer -> new TrainerInfo(
                        trainer.getUser().getUsername(),
                        trainer.getUser().getFirstName(),
                        trainer.getUser().getLastName(),
                        trainer.getTrainingTypes()
                )
        ).collect(Collectors.toList());
    }


    @Override
    public TrainerResponse updateTraineeTrainerList(Long id, UpdateTraineeTrainerListRequest updateRequest) {
        Trainee trainee = traineeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainee not found with ID: " + id));

        List<String> trainersUsername = updateRequest.getUsernames();
        List<Trainer> trainers = trainerRepo.findTraineeByUser_Username2(trainersUsername);
        trainee.setTrainer(trainers);
        traineeRepo.save(trainee);
        List<TrainerResponse> updateResponses = trainers.stream()
                .map(trainer -> new TrainerResponse(
                        trainer.getUser().getFirstName(),
                        trainer.getUser().getLastName(),
                        trainer.getUser().getUsername(),
                        trainer.getTrainingTypes()
                ))
                .collect(Collectors.toList());



        return new TrainerResponse(updateResponses);
    }

    @Override
    public TraineeResponse getTraineeTrainingList(String traineeUsername) {
        return null;
    }

    @Override
    public TraineeResponse activateTrainee(Long id) {
        return null;
    }

    @Override
    public TraineeResponse deactivateTrainee(Long id) {
        return null;
    }
}
