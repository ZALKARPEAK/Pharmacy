package com.example.Trainee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import com.example.Trainee.entity.additional.Id;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Builder
public class Trainer extends Id {
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    private Training_Types trainingTypes;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(mappedBy = "trainer",cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
    private List<Trainee> trainee;

    public Trainer() {
    }
}
