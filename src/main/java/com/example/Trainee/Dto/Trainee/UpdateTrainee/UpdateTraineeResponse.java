package com.example.Trainee.Dto.Trainee.UpdateTrainee;

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
public class UpdateTraineeResponse {
    private String username;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private boolean isActive;
    private List<TrainersList> trainersLists;

    public void setTraineeResponse(List<Trainer> trainers) {
        this.trainersLists = trainers.stream().map(
                trainer -> {
                    TrainersList trainersList = new TrainersList();
                    trainersList.setUsername(trainer.getUser().getUsername());
                    trainersList.setFirstName(trainer.getUser().getFirstName());
                    trainersList.setLastName(trainer.getUser().getLastName());
                    trainersList.setTrainingTypes(trainer.getTrainingTypes());
                    return trainersList;
                }
        ).collect(Collectors.toList());
    }
}
