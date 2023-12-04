package com.georgeifrim.HibernateDemo.entities.dto.requests;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TraineeRequestDto {

    private LocalDate date_of_birth;
    private String address;
    private int userId;


}
