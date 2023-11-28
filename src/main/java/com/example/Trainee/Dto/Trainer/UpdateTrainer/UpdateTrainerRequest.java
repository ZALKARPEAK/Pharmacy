package com.example.Trainee.Dto.Trainer.UpdateTrainer;

import com.example.Trainee.entity.Training_Types;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTrainerRequest {
    private String username;
    private String firstName;
    private String lastName;
    private Training_Types trainingTypes;
    private boolean isActive;

}
