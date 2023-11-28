package com.example.Trainee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import com.example.Trainee.entity.additional.Id;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Builder
public class Training extends Id {
    @ManyToOne
    @JsonIgnore
    private Trainee trainee;

    @ManyToOne
    @JsonIgnore
    private Trainer trainer;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Training_Types trainingTypes;

    private String TrainingName;
    private LocalDate TrainingDate;
    private int duration;

    public Training() {
    }


}
