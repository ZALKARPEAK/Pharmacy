package com.example.Trainee.Dto;

import com.example.Trainee.entity.Training_Types;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class TrainerResponse {
    private List<TrainerInfo> trainersInfo;


    public TrainerResponse(String firstName, String lastName, String userName, Training_Types specialization) {
        this.trainersInfo = List.of(new TrainerInfo(firstName, lastName, userName, specialization));
    }

    public TrainerResponse(List<TrainerResponse> updateResponses) {
        this.trainersInfo = updateResponses.stream()
                .flatMap(updateResponse -> updateResponse.getTrainersInfo().stream())
                .collect(Collectors.toList());
    }
}