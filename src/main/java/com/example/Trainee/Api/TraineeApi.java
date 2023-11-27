package com.example.Trainee.Api;

import com.example.Trainee.Dto.SimpleResponse;
import com.example.Trainee.Dto.Trainee.GetTraineeProfile.GetTraineeProfileRequest;
import com.example.Trainee.Dto.Trainee.GetTraineeProfile.GetTraineeProfileResponse;
import com.example.Trainee.Dto.TrainerResponse;
import com.example.Trainee.Dto.Trainee.RegistrationTrainee.TraineeRequest;
import com.example.Trainee.Dto.Trainee.UpdateTrainee.UpdateTraineeRequest;
import com.example.Trainee.Dto.Trainee.UpdateTrainee.UpdateTraineeResponse;
import com.example.Trainee.Dto.Trainee.UpdateTraineeTrainerList.UpdateTraineeTrainerListRequest;
import com.example.Trainee.Dto.TrainerInfo;
import com.example.Trainee.Dto.UserChangePasswordRequest;
import com.example.Trainee.Dto.UserCreateResponse;
import com.example.Trainee.Dto.UserCheckRequest;
import com.example.Trainee.Service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Trainee")
@RequiredArgsConstructor
public class TraineeApi {

    private final TraineeService traineeService;

    @PostMapping("/create-profile")
    public ResponseEntity<UserCreateResponse> createTraineeProfile(@RequestBody TraineeRequest traineeRequest) {
        UserCreateResponse createdProfile = traineeService.createTraineeProfile(traineeRequest);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    @GetMapping("/checkCredentialsTrainee")
    public ResponseEntity<SimpleResponse> checkCredentialsTrainee(@RequestBody UserCheckRequest userCheckRequest) {
        SimpleResponse response = traineeService.checkCredentialsTrainee(userCheckRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/changePasswordTrainee")
    public ResponseEntity<SimpleResponse> changePasswordTraineeByUsername(@RequestBody UserChangePasswordRequest userChangePasswordRequest){
        SimpleResponse response = traineeService.changeTraineePassword(userChangePasswordRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/select-profile")
    public ResponseEntity<GetTraineeProfileResponse> selectTraineeProfileByUsername(@RequestBody GetTraineeProfileRequest getTraineeProfileRequest) {
        GetTraineeProfileResponse getTraineeProfileResponse = traineeService.SelectTraineeProfileByUsername(getTraineeProfileRequest);
        return new ResponseEntity<>(getTraineeProfileResponse, HttpStatus.OK);
    }

    @PutMapping("/UpdateTrainee/{username}")
    public ResponseEntity<UpdateTraineeResponse> updateTrainee(@PathVariable String username, @RequestBody UpdateTraineeRequest updateTraineeRequest){
        UpdateTraineeResponse updateTraineeResponse = traineeService.updateTraineeProfile(username, updateTraineeRequest);
        return new ResponseEntity<>(updateTraineeResponse, HttpStatus.OK);
    }
    @DeleteMapping("/deleteTrainee")
    public ResponseEntity<SimpleResponse>response(@RequestBody GetTraineeProfileRequest delete){
        SimpleResponse getTraineeProfileRequest=traineeService.deleteTraineeProfileByUsername(delete);
        return new ResponseEntity<>(getTraineeProfileRequest, HttpStatus.OK);
    }

    @GetMapping("/getNotAssigned")
    public ResponseEntity<List<TrainerInfo>> getNotAssigned(@RequestBody GetTraineeProfileRequest request){
        List<TrainerInfo> trainerResponse = traineeService.getNotAssignedActiveTrainersListForTrainee(request);
        return new ResponseEntity<>(trainerResponse, HttpStatus.OK);
    }

    @PutMapping("/updateTrainerList/{traineeId}")
    public ResponseEntity<TrainerResponse> updateTrainer(@PathVariable Long traineeId, @RequestBody UpdateTraineeTrainerListRequest updateRequest) {
       TrainerResponse trainerResponse = traineeService.updateTraineeTrainerList(traineeId, updateRequest);
        return new ResponseEntity<>(trainerResponse, HttpStatus.OK);
    }
}
