package com.example.Trainee.Dto.Trainee.GetTraineeProfile;

import com.example.Trainee.Dto.TrainerInfo;
import com.example.Trainee.entity.Trainer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class GetTraineeProfileResponse {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private boolean isActive;
    private List<TrainerInfo> trainerResponses;

    public void setTrainerResponses(List<Trainer> trainer) {
        this.trainerResponses = trainer.stream()
                .map(trainers -> {
                    TrainerInfo trainerResponse = new TrainerInfo();
                    trainerResponse.setUserName(trainers.getUser().getUsername());
                    trainerResponse.setFirstName(trainers.getUser().getFirstName());
                    trainerResponse.setLastName(trainers.getUser().getLastName());
                    trainerResponse.setTrainingTypes(trainers.getTrainingTypes());
                    return trainerResponse;
                })
                .collect(Collectors.toList());
    }
}
