package com.georgeifrim.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.georgeifrim.entities.requests.Training;
import com.georgeifrim.entities.responses.Trainer;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
@Data
public class Trainee {


    private int id;

    private LocalDate date_of_birth;

    private String address;

    private List<Training> trainings;

    private List<Trainer> trainers;
    private User user;

}
