package com.georgeifrim.HibernateDemo.entities.dto.requests;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

public record TraineeRequestDto(
         String first_name,
         String last_name,
         LocalDate date_of_birth,
         String address
) {

    public String traineeRequestDtoUsername(){
        return first_name + "." + last_name;
    }
}
