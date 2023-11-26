package com.example.Trainee.Dto.Trainee.UpdateTrainee;

import com.example.Trainee.Dto.Trainee.GetTraineeProfile.TrainerResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTraineeRequest {
    private String username;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private boolean isActive;
}
