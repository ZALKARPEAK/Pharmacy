package com.example.Trainee.Pagination;

import com.example.Trainee.Dto.Trainee.GetTraineeProfile.GetTraineeProfileResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TraineePaginationResponse {

    private List<GetTraineeProfileResponse> trainees;
    private int page;
    private int size;

}
