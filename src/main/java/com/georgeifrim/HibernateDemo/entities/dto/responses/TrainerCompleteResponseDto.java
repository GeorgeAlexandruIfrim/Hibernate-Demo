package com.georgeifrim.HibernateDemo.entities.dto.responses;

public record TrainerCompleteResponseDto(
        String username,
        String first_name,
        String last_name,
        String trainingTypeName
) {
}
