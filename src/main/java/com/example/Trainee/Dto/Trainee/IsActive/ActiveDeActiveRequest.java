package com.example.Trainee.Dto.Trainee.IsActive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActiveDeActiveRequest {
    private String username;
    private boolean isActive;
}
