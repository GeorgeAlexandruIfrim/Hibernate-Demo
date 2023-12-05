package com.georgeifrim.HibernateDemo.entities.dto.requests;

public record TrainerRequestDto(
        String first_name,
        String last_name,
        String trainingTypeName
) {

    public String trainerRequestDtoUsername(){
        return first_name + "." + last_name;
    }

}
