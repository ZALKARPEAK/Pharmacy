package com.example.Trainee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import com.example.Trainee.entity.additional.Id;

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

    @OneToOne
    private Trainee trainee;

    public Trainer() {
    }
}
