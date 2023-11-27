package com.example.Trainee.Api;

import com.example.Trainee.Dto.SimpleResponse;
import com.example.Trainee.Dto.Trainer.GetTrainerProfile.GetTrainerProfileRequest;
import com.example.Trainee.Dto.Trainer.GetTrainerProfile.GetTrainerProfileResponse;
import com.example.Trainee.Dto.Trainer.UpdateTrainer.UpdateTrainerRequest;
import com.example.Trainee.Dto.Trainer.UpdateTrainer.UpdateTrainerResponse;
import com.example.Trainee.Dto.UserChangePasswordRequest;
import com.example.Trainee.Dto.UserCheckRequest;
import com.example.Trainee.Dto.Trainer.RegistrationTrainer.TrainerRequest;
import com.example.Trainee.Dto.UserCreateResponse;
import com.example.Trainee.Service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Trainer")
@RequiredArgsConstructor
public class TrainerApi {

    private final TrainerService trainerService;

    @PostMapping("/create-profile")
    public ResponseEntity<UserCreateResponse> createTrainerProfile(@RequestBody TrainerRequest trainerRequest) {
        UserCreateResponse response = trainerService.createTrainerProfile(trainerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/checkCredentialsTrainer")
    public ResponseEntity<SimpleResponse> checkCredentialsTrainee(@RequestBody UserCheckRequest userCheckRequest){
        SimpleResponse response = trainerService.checkCredentialsTrainer(userCheckRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/changePasswordTrainer")
    public ResponseEntity<SimpleResponse> changePasswordTrainerByUsername(@RequestBody UserChangePasswordRequest userChangePasswordRequest){
        SimpleResponse response = trainerService.changeTrainerPassword(userChangePasswordRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/select-profile")
    public ResponseEntity<GetTrainerProfileResponse> selectTraineeProfileByUsername(@RequestBody GetTrainerProfileRequest getTraineeProfileRequest) {
        GetTrainerProfileResponse getTraineeProfileResponse = trainerService.SelectTrainerProfileByUsername(getTraineeProfileRequest);
        return new ResponseEntity<>(getTraineeProfileResponse, HttpStatus.OK);
    }

    @PutMapping("/UpdateTrainer/{username}")
    public ResponseEntity<UpdateTrainerResponse> updateTrainer(@PathVariable String username, @RequestBody UpdateTrainerRequest updateTrainerRequest){
        UpdateTrainerResponse updateTrainerProfile = trainerService.updateTrainerProfile(username, updateTrainerRequest);
        return new ResponseEntity<>(updateTrainerProfile, HttpStatus.OK);
    }
}
