package com.example.Trainee.Dto.Trainer.GetTrainerProfile;

import com.example.Trainee.entity.Training_Types;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetTrainerProfileResponse {
    private String firstName;
    private String lastName;
    private Training_Types trainingTypes;
    private boolean isActive;
    private List<TraineeResponse> traineeResponses;

    public GetTrainerProfileResponse(String firstName, String lastName, Training_Types trainingTypes, boolean isActive, List<TraineeResponse> traineeResponses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.trainingTypes = trainingTypes;
        this.isActive = isActive;
        this.traineeResponses = traineeResponses;
    }
}
