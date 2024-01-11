package com.georgeifrim.entities.requests;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
public class TrainingWithHttpMethod {

    private LocalDate date;
    private int durationMinutes;
    private String name;
    private int trainee_id;
    private int trainer_id;
    private int training_type_id;
    private String httpMethod;
}
