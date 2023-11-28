package com.example.Trainee.Dto;

import com.example.Trainee.entity.Training_Types;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TrainerInfo {

    private String firstName;
    private String lastName;
    private String userName;
    private Training_Types trainingTypes;

    public TrainerInfo(String firstName, String lastName, String userName, Training_Types trainingTypes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.trainingTypes = trainingTypes;
    }
}
