package com.georgeifrim.mongoEntities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Trainings {

    private LocalDate date;
    private int duration_minutes;
    private String name;
}
