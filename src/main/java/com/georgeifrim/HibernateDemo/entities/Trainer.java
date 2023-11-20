package com.georgeifrim.HibernateDemo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "trainingType_id")
    private TrainingType trainingType;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.PERSIST)
    private List<Training> trainings;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Trainee> trainees;

    @OneToOne
    private User user;

}
