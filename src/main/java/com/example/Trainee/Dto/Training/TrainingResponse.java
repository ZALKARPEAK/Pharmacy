package com.example.Trainee.Dto.Training;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TrainingResponse {
    private Long id;
    private String TrainingName;
    private Date TrainingDate;
    private int duration;

    public TrainingResponse(Long id, String trainingName, Date trainingDate, int duration) {
        this.id = id;
        TrainingName = trainingName;
        TrainingDate = trainingDate;
        this.duration = duration;
    }
}
