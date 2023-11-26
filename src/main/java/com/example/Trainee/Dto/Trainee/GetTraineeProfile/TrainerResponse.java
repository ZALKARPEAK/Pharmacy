package com.example.Trainee.Dto.Trainee.GetTraineeProfile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TrainerResponse {
    private String trainerUsername;
    private String trainerFirstName;
    private String trainerLastName;
    private String trainerSpecialization;
}
