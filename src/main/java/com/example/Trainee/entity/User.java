package com.example.Trainee.entity;


import com.example.Trainee.entity.additional.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Table(name = "users")
@Getter
@AllArgsConstructor
public class User extends Id {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private Trainee trainee;
    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private Trainer trainer;

    public User() {
    }
}
