package com.georgeifrim.HibernateDemo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TrainingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "trainingType", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Trainer> trainers;

    @OneToMany(mappedBy = "trainingType")
    @JsonManagedReference
    private List<Training> trainings;

    private String name;

    public TrainingType(String name){
        this.name = name;
    }

    public TrainingType(){}

}
