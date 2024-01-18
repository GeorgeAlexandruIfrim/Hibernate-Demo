package com.georgeifrim.mongoEntities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Trainings {

    private LocalDate date;
    private int duration_minutes;
    private String name;
}
