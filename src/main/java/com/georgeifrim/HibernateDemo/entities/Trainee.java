package com.georgeifrim.HibernateDemo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
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
    private List<Trainer> trainers;

    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;


}
