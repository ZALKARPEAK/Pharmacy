package com.example.Trainee.Dto.Training_Types;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Training_TypesResponse {
    private Long id;
    private String TrainingTypeName;

    public Training_TypesResponse(Long id, String trainingTypeName) {
        this.id = id;
        TrainingTypeName = trainingTypeName;
    }
}
