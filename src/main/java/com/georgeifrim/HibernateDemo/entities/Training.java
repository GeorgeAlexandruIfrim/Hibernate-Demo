package com.georgeifrim.HibernateDemo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "trainee_id")
    @JsonBackReference
    private Trainee trainee;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @JsonBackReference
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "trainingType_id")
    @JsonBackReference
    private TrainingType trainingType;

    private String name;
    private LocalDate date;
    private int durationMinutes;

    public Training(){}
    public Training(String name, LocalDate date, int durationMinutes, Trainer trainer, Trainee trainee){
        this.name=name;
        this.date=date;
        this.durationMinutes=durationMinutes;
        this.trainer=trainer;
        this.trainee=trainee;
    }

}
