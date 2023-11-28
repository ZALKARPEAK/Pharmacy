package com.example.Trainee.Dto.Trainer.getTrainings;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResponseTrainers {

    private String trainingName;
    private LocalDate trainingDate;
    private int Duration;
    private String trainerName;

    public ResponseTrainers(String trainingName, LocalDate trainingDate, int duration, String trainerName) {
        this.trainingName = trainingName;
        this.trainingDate = trainingDate;
        Duration = duration;
        this.trainerName = trainerName;
    }
}