package com.georgeifrim.HibernateDemo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private LocalDate date_of_birth;

    private String address;

    @OneToMany(mappedBy = "trainee", cascade = CascadeType.PERSIST)
    private List<Training> trainings;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "trainee_trainer",
            joinColumns = @JoinColumn(name = "trainee_id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id")
    )
    private List<Trainer> trainers;

    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;


}
