package com.georgeifrim.HibernateDemo.entities.dto.requests;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TrainingRequestDto {

    private LocalDate date;
    private int durationMinutes;
    private String name;
    private int trainee_id;
    private int trainer_id;
    private int training_type_id;

    public TrainingRequestDto (TrainingRequestWithHttpMethod training){
        this.date = training.getDate();
        this.durationMinutes = training.getDurationMinutes();
        this.name = training.getName();
        this.trainee_id = training.getTrainee_id();
        this.trainer_id = training.getTrainer_id();
        this.training_type_id = training.getTraining_type_id();
    }
}
