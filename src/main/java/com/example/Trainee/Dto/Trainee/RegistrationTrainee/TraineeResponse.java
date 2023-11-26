package com.example.Trainee.Dto.Trainee.RegistrationTrainee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TraineeResponse {
    private Long id;
    private Date date_of_birth;
    private String address;
    private String first_Name;
    private String last_Name;

}
