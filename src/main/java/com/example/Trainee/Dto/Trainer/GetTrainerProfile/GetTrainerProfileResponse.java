package com.example.Trainee.Dto.Trainer.GetTrainerProfile;

import com.example.Trainee.entity.Trainee;
import com.example.Trainee.entity.Training_Types;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class GetTrainerProfileResponse {
    private String firstName;
    private String lastName;
    private Training_Types trainingTypes;
    private boolean isActive;
    private List<TraineeResponse> traineeResponses;

    public void setTraineeResponses(List<Trainee> trainees) {
        this.traineeResponses = trainees.stream().map(
                trainee -> {
                    TraineeResponse traineeResponse = new TraineeResponse();
                    traineeResponse.setUsername(trainee.getUser().getUsername());
                    traineeResponse.setFirstName(trainee.getUser().getFirstName());
                    traineeResponse.setLastName(trainee.getUser().getLastName());
                    return traineeResponse;
                }
        ).collect(Collectors.toList());
    }
}
