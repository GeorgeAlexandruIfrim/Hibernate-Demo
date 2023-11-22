package com.georgeifrim.HibernateDemo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "trainingType_id")
    @JsonBackReference
    private TrainingType trainingType;

    @JsonManagedReference
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Training> trainings;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "trainers")
    @JsonIgnoreProperties("trainers")
    private List<Trainee> trainees;

    @OneToOne
    private User user;
}
