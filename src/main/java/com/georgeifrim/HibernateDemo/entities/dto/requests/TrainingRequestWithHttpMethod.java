package com.georgeifrim.HibernateDemo.entities.dto.requests;

import lombok.Data;
import org.springframework.http.HttpMethod;

import java.time.LocalDate;

@Data
public class TrainingRequestWithHttpMethod {

    private LocalDate date;
    private int durationMinutes;
    private String name;
    private int trainee_id;
    private int trainer_id;
    private int training_type_id;
    private HttpMethod httpMethod;
}
