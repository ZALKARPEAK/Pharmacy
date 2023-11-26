package com.example.Trainee.entity;

import com.example.Trainee.entity.additional.Id;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Builder
public class Trainee extends Id {
    private Date date_of_birth;
    private String address;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private User user;

    @OneToOne
    private Trainer trainer;

    public Trainee() {
    }

}
