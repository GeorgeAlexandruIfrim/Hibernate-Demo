package com.georgeifrim.HibernateDemo.entities.dto.responses;

import java.util.List;

public record TrainerCompleteResponseDto(
        String username,
        String firstName,
        String lastName,
        String trainingTypeName,
        List<TraineeResponseDto> traineeList
) {
}
