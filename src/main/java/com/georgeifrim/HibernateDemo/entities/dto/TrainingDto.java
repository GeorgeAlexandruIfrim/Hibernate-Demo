package com.georgeifrim.HibernateDemo.entities.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TrainingDto {

    private LocalDate date;
    private int duration_minutes;
    private String name;
    private int trainee_id;
    private int trainer_id;
    private int training_type_id;

}
