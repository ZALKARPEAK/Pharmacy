package com.example.Trainee.Dto.Trainer.GetTrainerProfile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TraineeResponse {
    private String username;
    private String firstName;
    private String lastName;

    public TraineeResponse(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
