package com.example.Trainee.Dto.Trainee.UpdateTraineeTrainerList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTraineeTrainerListRequest {
    private List<String> usernames;
    private String traineeUsername;
}