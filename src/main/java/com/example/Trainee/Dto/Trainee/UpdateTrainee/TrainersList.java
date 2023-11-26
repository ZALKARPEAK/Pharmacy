package com.example.Trainee.Dto.Trainee.UpdateTrainee;

import com.example.Trainee.entity.Training_Types;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TrainersList {
    private String username;
    private String firstName;
    private String lastName;
    private Training_Types trainingTypes;
}
