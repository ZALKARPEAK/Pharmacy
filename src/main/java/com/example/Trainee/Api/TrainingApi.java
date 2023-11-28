package com.example.Trainee.Api;

import com.example.Trainee.Dto.SimpleResponse;
import com.example.Trainee.Dto.Training.CreateTraining.AddTrainingRequest;
import com.example.Trainee.Service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Training")
@RequiredArgsConstructor
public class TrainingApi {

    private final TrainingService trainingService;

    @PostMapping("/create")
    private ResponseEntity<SimpleResponse> createTraining(@RequestBody AddTrainingRequest addTrainingRequest){
        trainingService.addTraining(addTrainingRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
