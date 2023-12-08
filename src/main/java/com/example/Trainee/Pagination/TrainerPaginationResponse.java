package com.example.Trainee.Pagination;

import com.example.Trainee.Dto.Trainer.GetTrainerProfile.GetTrainerProfileResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TrainerPaginationResponse {
    private List<GetTrainerProfileResponse> trainees;
    private int page;
    private int size;
}
