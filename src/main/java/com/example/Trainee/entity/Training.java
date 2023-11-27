package com.example.Trainee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import com.example.Trainee.entity.additional.Id;

import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Builder
public class Training extends Id {
    @ManyToOne
    private Trainee trainee;

    @ManyToOne
    private Trainer trainer;

    @ManyToOne
    private Training_Types trainingTypes;

    private String TrainingName;
    private Date TrainingDate;
    private int duration;

    public Training() {
    }
}
