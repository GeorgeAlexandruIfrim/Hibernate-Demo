package com.georgeifrim.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.georgeifrim.entities.requests.Training;
import com.georgeifrim.entities.responses.Trainer;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
public class TrainingType {

    private int id;

    private List<Trainer> trainers;


    private List<Training> trainings;

    private String name;

}
