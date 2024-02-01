package com.georgeifrim.entities.responses;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TrainingResponse {

    private int id;
    private String name;
    private LocalDate date;
    private int durationMinutes;

    public Integer getYear(){
        return date.getYear();
    }

    public String getMonth(){
        return date.getMonth().name();
    }
}
