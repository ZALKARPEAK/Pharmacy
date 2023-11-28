package com.example.Trainee.Dto.Training.getTraineeTrainingsList;

import com.example.Trainee.entity.Training_Types;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GetTraineeTrainingsListRequest {
    private String username;
    private LocalDate periodFrom;
    private LocalDate periodTo;
    private String trainerName;
    private Training_Types trainingTypes;
}
