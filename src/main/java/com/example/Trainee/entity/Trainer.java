package com.example.Trainee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import com.example.Trainee.entity.additional.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Builder
public class Trainer extends Id {
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Training_Types trainingTypes;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User user;

    @ManyToMany(mappedBy = "trainer",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Trainee> trainee;

    @Transient
    private List<Training> trainings;

    public Trainer() {
        this.trainings = new ArrayList<>();
    }

    public void addTraining(Training training) {
        this.trainings.add(training);
        training.setTrainer(this);
    }


}
