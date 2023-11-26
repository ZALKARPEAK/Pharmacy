package com.example.Trainee.entity;

import jakarta.persistence.CascadeType;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Trainee trainee;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Trainer trainer;
    @ManyToOne(fetch = FetchType.LAZY)
    private Training_Types trainingTypes;

    private String TrainingName;
    private Date TrainingDate;
    private int duration;

    public Training() {
    }
}
