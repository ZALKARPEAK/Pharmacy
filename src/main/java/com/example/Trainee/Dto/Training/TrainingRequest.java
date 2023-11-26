package com.example.Trainee.Dto.Training;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TrainingRequest {
    private String TrainingName;
    private Date TrainingDate;
    private int duration;
}
