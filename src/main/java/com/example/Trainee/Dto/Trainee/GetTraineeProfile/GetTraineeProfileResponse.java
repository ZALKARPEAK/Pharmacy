package com.example.Trainee.Dto.Trainee.GetTraineeProfile;

import com.example.Trainee.entity.Trainer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetTraineeProfileResponse {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private boolean isActive;
    private List<TrainerResponse> trainerResponses;

}
