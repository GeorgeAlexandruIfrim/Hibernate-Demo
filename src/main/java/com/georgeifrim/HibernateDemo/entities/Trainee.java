package com.georgeifrim.HibernateDemo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private LocalDate date_of_birth;

    private String address;

    @OneToMany(mappedBy = "trainee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Training> trainings;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "trainee_trainer",
            joinColumns = @JoinColumn(name = "trainee_id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id")
    )
    private List<Trainer> trainers;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trainee)) return false;
        Trainee trainee = (Trainee) o;
        return getDate_of_birth().equals(trainee.getDate_of_birth()) && getAddress().equals(trainee.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate_of_birth(), getAddress());
    }
}
