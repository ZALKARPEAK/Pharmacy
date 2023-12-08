package com.example.Trainee.Dto.Trainee.RegistrationTrainee;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TraineeRequest {
    @NotNull
    private String first_Name;
    @NotNull
    private String last_Name;

    private Date date_of_birth;
    private String address;
}